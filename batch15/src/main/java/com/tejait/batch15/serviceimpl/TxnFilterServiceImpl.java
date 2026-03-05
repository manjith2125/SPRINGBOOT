package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.enums.InstrumentType;
import com.tejait.batch15.enums.TransactionStatus;
import com.tejait.batch15.model.Transcation;
import com.tejait.batch15.repository.TxnFilterRepository;
import com.tejait.batch15.service.TxnFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TxnFilterServiceImpl implements TxnFilterService {

    private final TxnFilterRepository txnFilterRepository;

    @Override
    public List<Transcation> filterTransactions(Integer appId,
                                                List<String> statusOrInstrumentTypesList) {

        List<String> statusList = new ArrayList<>();
        List<String> instrumentList = new ArrayList<>();

        for (String value : statusOrInstrumentTypesList) {

            try {
                TransactionStatus status = TransactionStatus.valueOf(value);
                statusList.add(status.name());
                continue;
            } catch (Exception ignored) {}

            try {
                InstrumentType instrument = InstrumentType.valueOf(value);
                instrumentList.add(instrument.name());
            } catch (Exception ignored) {}
        }

        if (!statusList.isEmpty() && !instrumentList.isEmpty()) {

            return txnFilterRepository
                    .findByAppIdAndTransactionStatusInAndInstrumentIn(
                            appId,
                            statusList,
                            instrumentList
                    );
        }

        if (!statusList.isEmpty()) {

            return txnFilterRepository
                    .findByAppIdAndTransactionStatusIn(appId, statusList);
        }

        if (!instrumentList.isEmpty()) {

            return txnFilterRepository
                    .findByAppIdAndInstrumentIn(appId, instrumentList);
        }

        return txnFilterRepository.findByAppId(appId);
    }
}