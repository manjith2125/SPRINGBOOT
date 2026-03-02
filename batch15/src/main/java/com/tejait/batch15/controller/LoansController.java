package com.tejait.batch15.controller;

import com.tejait.batch15.model.*;
import com.tejait.batch15.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("loans")
public class LoansController {

    private final LoanService service;
   @PostMapping("applyLoan")
    public ResponseEntity<Loans> applyLoan(@RequestBody Loans loan) {

        Loans savedLoans = service.applyingLoan(loan);

        // if appId exists → UPDATE
        if (loan.getAppId() != 0) {
            return new ResponseEntity<>(savedLoans, HttpStatus.OK);
        }

        // new record → SAVE
        return new ResponseEntity<>(savedLoans, HttpStatus.CREATED);
    }

    // 🔥 FIXED HERE
    @GetMapping("loanTaskboard")
    public ResponseEntity<List<Loans>> getAllLoanAppliers() {

        List<Loans> list = service.getAllLoanAppliers();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getProductDetails/{appId}")
    public ResponseEntity<BusinessProduct> getBusinessProducts(@PathVariable int appId) {
        return ResponseEntity.ok(service.getBusinessProductsByAppId(appId));
    }

    @GetMapping("/getCompanyDetails/{appId}")
    public ResponseEntity<CompanyDetails> getCompanyDetails(@PathVariable int appId) {
        return ResponseEntity.ok(service.getCompanyDetailsByAppId(appId));
    }

    @GetMapping("/getCompanyAddress/{appId}")
    public ResponseEntity<CompanyAddress> getCompanyAddressDetails(@PathVariable int appId) {
        return ResponseEntity.ok(service.getCompanyAddressDetailsByAppId(appId));
    }
}