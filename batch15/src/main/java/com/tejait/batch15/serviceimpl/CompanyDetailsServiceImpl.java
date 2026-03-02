package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.CompanyDetails;
import com.tejait.batch15.repository.CompanyDetailsRepository;
import com.tejait.batch15.service.CompanyDetailsService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

    CompanyDetailsRepository repository;


    @Override
    public CompanyDetails saveCompanyDetails(CompanyDetails company) {

        // check existing record using appId
        CompanyDetails existing =
                repository.findByAppId(company.getAppId());

        // if exists → convert to UPDATE
        if (existing != null) {
            company.setId(existing.getId());
        }

        return repository.save(company);
    }
}
