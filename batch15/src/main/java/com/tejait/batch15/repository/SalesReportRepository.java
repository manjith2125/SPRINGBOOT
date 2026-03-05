package com.tejait.batch15.repository;

import com.tejait.batch15.model.SalesReportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SalesReportRepository extends JpaRepository<SalesReportDetails, Integer> {

    List<SalesReportDetails> findAllByAppId(int appId);

    @Modifying
    @Transactional
    void deleteAllByAppId(int appId);
}
