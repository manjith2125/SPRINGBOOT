package com.tejait.batch15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejait.batch15.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
