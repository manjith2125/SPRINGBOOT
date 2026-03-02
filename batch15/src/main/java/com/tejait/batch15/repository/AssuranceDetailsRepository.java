package com.tejait.batch15.repository;

import com.tejait.batch15.model.AssuranceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssuranceDetailsRepository
        extends JpaRepository<AssuranceDetails, Integer> {

    List<AssuranceDetails> findAllByAppId(int appId);
}