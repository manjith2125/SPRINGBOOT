package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.BusinessProduct;
import com.tejait.batch15.repository.BusinessProductRepository;
import com.tejait.batch15.service.BusinessProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BusinessProductServiceImpl implements BusinessProductService {

    BusinessProductRepository repository;

    @Override
    public BusinessProduct saveBusinessProducts(BusinessProduct product) {

        // check if record already exists for this appId
        BusinessProduct existing =
                repository.findByAppId(product.getAppId());

        // if exists → convert save into update
        if (existing != null) {
            product.setId(existing.getId());
        }

        return repository.save(product);
    }
}