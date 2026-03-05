package com.tejait.batch15.repository;

import com.tejait.batch15.model.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TxnFilterRepository extends JpaRepository<Transcation, Integer> {

    List<Transcation> findByAppId(Integer appId);

    List<Transcation> findByAppIdAndTransactionStatusIn(Integer appId, List<String> statusList);

    List<Transcation> findByAppIdAndInstrumentIn(Integer appId, List<String> instrumentList);

    List<Transcation> findByAppIdAndTransactionStatusInAndInstrumentIn(
            Integer appId,
            List<String> statusList,
            List<String> instrumentList
    );
}