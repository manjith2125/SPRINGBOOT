package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.Transcation;
import com.tejait.batch15.repository.TranscationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TxnsStatementServiceImpl {

    private final TranscationRepository transcationRepository;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<Transcation> getTransactions(Integer appId, String duration) {

        List<Transcation> transactions =
                transcationRepository.findAllByAppId(appId);

        // If duration is null return all transactions
        if (duration == null || duration.isBlank()) {
            return transactions;
        }

        LocalDate today = LocalDate.now();
        LocalDate startDate;
        LocalDate endDate;

        switch (duration.trim().toLowerCase()) {

            case "last month":

                YearMonth lastMonth = YearMonth.from(today.minusMonths(1));
                startDate = lastMonth.atDay(1);
                endDate = lastMonth.atEndOfMonth();
                break;

            case "last 3 months":

                startDate = today.minusMonths(3);
                endDate = today;
                break;

            case "last 6 months":

                startDate = today.minusMonths(6);
                endDate = today;
                break;

            case "last year":

                startDate = today.minusYears(1);
                endDate = today;
                break;

            default:
                return transactions;
        }

        LocalDate finalStartDate = startDate;
        LocalDate finalEndDate = endDate;

        return transactions.stream()
                .filter(txn -> {

                    try {

                        if (txn.getTransactionDate() == null) {
                            return false;
                        }

                        // DB value example: "05-01-2026 11:41"
                        // Extract only date part
                        String datePart = txn.getTransactionDate().substring(0, 10);

                        LocalDate txnDate = LocalDate.parse(datePart, formatter);

                        return !txnDate.isBefore(finalStartDate)
                                && !txnDate.isAfter(finalEndDate);

                    } catch (Exception e) {
                        return false;
                    }

                })
                .collect(Collectors.toList());
    }
}