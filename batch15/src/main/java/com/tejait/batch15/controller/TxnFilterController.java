package com.tejait.batch15.controller;

import com.tejait.batch15.model.Transcation;
import com.tejait.batch15.service.TxnFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class TxnFilterController {

    private final TxnFilterService txnFilterService;

    @GetMapping("/filtertransactions/{appId}")
    public List<Transcation> filterTransactions(
            @PathVariable Integer appId,
            @RequestParam List<String> statusOrInstrumentTypesList
    ) {

        return txnFilterService.filterTransactions(appId, statusOrInstrumentTypesList);
    }
}