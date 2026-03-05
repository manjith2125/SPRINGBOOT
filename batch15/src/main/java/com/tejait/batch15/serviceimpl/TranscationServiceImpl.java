package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.Transcation;
import com.tejait.batch15.repository.TranscationRepository;
import com.tejait.batch15.service.TranscationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TranscationServiceImpl implements TranscationService {

    private final TranscationRepository repository;

    // =========================
    // CSV UPLOAD
    // =========================

    @Override
    @Transactional
    public synchronized void uploadCsv(Integer appId, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("CSV file is empty");
        }

        try {
            // 🔥 STEP 1: Delete existing records for this appId
            repository.deleteAllByAppId(appId);
            repository.flush();

            // 🔥 STEP 2: Reset AUTO_INCREMENT
            repository.resetAutoIncrement();

            // 🔥 STEP 3: Parse CSV
            List<Transcation> list = parseCsv(file, appId);

            // 🔥 STEP 4: Save fresh records
            repository.saveAll(list);

        } catch (Exception e) {
            throw new RuntimeException("CSV upload failed: " + e.getMessage());
        }
    }

    // =========================
    // SAVE CHANGES FROM UI
    // =========================

    @Override
    @Transactional
    public synchronized void saveAll(Integer appId, List<Transcation> list) {

        if (list == null || list.isEmpty()) return;

        try {
            // 🔥 STEP 1: Delete old data
            repository.deleteAllByAppId(appId);
            repository.flush();

            // 🔥 STEP 2: Reset AUTO_INCREMENT
            repository.resetAutoIncrement();

            // 🔥 STEP 3: Prepare new data
            list.forEach(t -> {
                t.setId(null);      // Important
                t.setAppId(appId);  // Ensure correct appId
            });

            // 🔥 STEP 4: Save
            repository.saveAll(list);

        } catch (Exception e) {
            throw new RuntimeException("Save changes failed: " + e.getMessage());
        }
    }

    // =========================
    // FETCH DATA
    // =========================

    @Override
    public List<Transcation> getAllByAppId(Integer appId) {
        return repository.findAllByAppId(appId);
    }

    // =========================
    // CSV PARSER
    // =========================

    private List<Transcation> parseCsv(MultipartFile file, Integer appId) {

        List<Transcation> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                // Skip header
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] cols = line.split(",");

                if (cols.length < 9) continue;

                Transcation t = new Transcation();

                t.setAppId(appId);
                t.setTransactionDate(cols[0].trim());
                t.setActivity(cols[1].trim());
                t.setInstrument(cols[2].trim());
                t.setTxnId(cols[3].trim());
                t.setComment(cols[4].trim());
                t.setDebtAmt(parseDouble(cols[5]));
                t.setCreditAmt(parseDouble(cols[6]));
                t.setTransactionBreakup(cols[7].trim());
                t.setTransactionStatus(cols[8].trim());

                list.add(t);
            }

        } catch (Exception e) {
            throw new RuntimeException("CSV parsing failed");
        }

        return list;
    }

    // =========================
    // SAFE DOUBLE PARSER
    // =========================

    private Double parseDouble(String val) {
        try {
            if (val == null || val.isEmpty()) return 0.0;
            val = val.replaceAll("[^0-9.]", "");
            return val.isEmpty() ? 0.0 : Double.parseDouble(val);
        } catch (Exception e) {
            return 0.0;
        }
    }
}