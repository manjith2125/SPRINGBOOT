package com.tejait.batch15.repository;

import com.tejait.batch15.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans, Integer> {

    Optional<Loans> findByMobile(long mobile);

    Optional<Loans> findByMailId(String mailId);

    Loans findByAppId(int appId);

}
