package com.tejait.batch15.controller;

import com.tejait.batch15.model.CompanyDetails;
import com.tejait.batch15.service.CompanyDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loans")
@AllArgsConstructor
public class CompanyDetailsController {

    private final CompanyDetailsService service;

    @RequestMapping(
            value = "saveCompany/{appId}",
            method = {RequestMethod.POST, RequestMethod.PUT}
    )
    public ResponseEntity<CompanyDetails> saveCompanyDetails(
            @PathVariable int appId,
            @RequestBody CompanyDetails company) {

        // bind appId from URL
        company.setAppId(appId);

        CompanyDetails details =
                service.saveCompanyDetails(company);

        // UPDATE case
        if (company.getId() != 0) {
            return new ResponseEntity<>(
                    details,
                    HttpStatus.OK
            );
        }

        // SAVE case
        return new ResponseEntity<>(
                details,
                HttpStatus.CREATED
        );
    }
}