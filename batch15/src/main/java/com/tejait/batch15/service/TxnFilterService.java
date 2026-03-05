package com.tejait.batch15.service;

import com.tejait.batch15.model.Transcation;

import java.util.List;

public interface TxnFilterService {

    List<Transcation> filterTransactions(Integer appId,
                                         List<String> statusOrInstrumentTypesList);
}