package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.CompanyAddress;
import com.tejait.batch15.repository.CompanyAddressRepository;
import com.tejait.batch15.service.CompanyAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CompanyAddressServiceImpl implements CompanyAddressService {
    CompanyAddressRepository repository;

    @Override
    public CompanyAddress saveCompanyAddress(CompanyAddress address) {

        // check if record already exists for this appId
        CompanyAddress existing =
                repository.findByAppId(address.getAppId());

        // if exists → convert save into update
        if (existing != null) {
            address.setId(existing.getId());
        }

        return repository.save(address);
    }
}
