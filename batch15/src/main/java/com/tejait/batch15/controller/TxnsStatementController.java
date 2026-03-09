package com.tejait.batch15.controller;

import com.tejait.batch15.model.Transcation;
import com.tejait.batch15.serviceimpl.TxnsStatementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class TxnsStatementController {

    private final TxnsStatementServiceImpl service;

    @GetMapping("/fetchtransactions/{appId}")
    public List<Transcation> fetchTransactions(
            @PathVariable Integer appId,
            @RequestParam(required = false) String duration) {

        return service.getTransactions(appId, duration);
    }
}