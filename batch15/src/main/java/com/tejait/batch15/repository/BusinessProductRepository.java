package com.tejait.batch15.repository;

import com.tejait.batch15.model.BusinessProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BusinessProductRepository extends JpaRepository<BusinessProduct, Integer> {

    // BusinessProductRepository
    BusinessProduct findByAppId(int appId);
}
