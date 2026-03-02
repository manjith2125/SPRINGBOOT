package com.tejait.batch15.controller;

import com.tejait.batch15.model.CompanyAddress;
import com.tejait.batch15.service.CompanyAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("loans")
public class CompanyAddressController {

    private final CompanyAddressService service;

    @RequestMapping(
            value = "saveCompanyAddress/{appId}",
            method = {RequestMethod.POST, RequestMethod.PUT}
    )
    public ResponseEntity<CompanyAddress> saveCompanyAddress(
            @PathVariable int appId,
            @RequestBody CompanyAddress address) {

        // bind appId from URL
        address.setAppId(appId);

        CompanyAddress companyAddress =
                service.saveCompanyAddress(address);

        // UPDATE case
        if (address.getId() != 0) {
            return new ResponseEntity<>(companyAddress, HttpStatus.OK);
        }

        // SAVE case
        return new ResponseEntity<>(companyAddress, HttpStatus.CREATED);
    }
}