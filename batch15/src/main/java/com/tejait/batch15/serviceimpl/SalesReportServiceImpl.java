package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.SalesReportDetails;
import com.tejait.batch15.repository.SalesReportRepository;
import com.tejait.batch15.service.SalesReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SalesReportServiceImpl implements SalesReportService {

    private final SalesReportRepository repository;

    @Override
    @Transactional
    public void uploadExcel(int appId, MultipartFile file) {
        if (file == null || file.isEmpty()) throw new RuntimeException("File is empty");

        // Clear existing data for this appId before saving new data
        repository.deleteAllByAppId(appId);

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<SalesReportDetails> list = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();

            // Row Index 6 (Excel Row 7) is where actual data starts
            for (int i = 6; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String dateVal = formatter.formatCellValue(row.getCell(0)).trim();
                if (dateVal.isEmpty()) continue;

                SalesReportDetails s = new SalesReportDetails();
                s.setAppId(appId);

                // MAPPING TO MATCH YOUR UI BINDINGS
                s.setDate(dateVal);
                s.setOrderno(formatter.formatCellValue(row.getCell(1)));
                s.setInvoiceno(formatter.formatCellValue(row.getCell(2)));
                s.setPartyName(formatter.formatCellValue(row.getCell(3)));
                s.setPartyPhoneNum(formatter.formatCellValue(row.getCell(5))); // Col 5

                s.setTotalAmount(parseSafeDouble(row.getCell(7)));             // Col 7
                s.setRecievedOrPaidAmount(parseSafeDouble(row.getCell(9)));    // Col 9
                s.setBalanceAmount(parseSafeDouble(row.getCell(11)));          // Col 11

                list.add(s);
            }
            repository.saveAll(list);
            log.info("Successfully saved {} records for appId {}", list.size(), appId);
        } catch (Exception e) {
            log.error("Excel processing failed", e);
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private Double parseSafeDouble(Cell cell) {
        if (cell == null) return 0.0;
        try {
            if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                return cell.getNumericCellValue();
            }
            String val = new DataFormatter().formatCellValue(cell).replaceAll("[^0-9.]", "");
            return val.isEmpty() ? 0.0 : Double.parseDouble(val);
        } catch (Exception e) {
            return 0.0;
        }
    }

    @Override
    public List<SalesReportDetails> getAllByAppId(int appId) {
        return repository.findAllByAppId(appId);
    }

    @Override
    @Transactional
    public void saveAll(int appId, List<SalesReportDetails> list) {
        if (list == null) return;
        list.forEach(d -> d.setAppId(appId));
        repository.saveAll(list);
    }
}
