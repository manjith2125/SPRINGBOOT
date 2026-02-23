package com.tejait.batch15.service;

import com.tejait.batch15.Dto.AccountHolderDto;
import com.tejait.batch15.Dto.AccountResponseDto;
import com.tejait.batch15.model.Account;

public interface AccountService {

	Account saveAccount(Account acc);

	AccountResponseDto getbySensitiveAccDetails(Long id);

	AccountHolderDto getByHolder(long id);

}
