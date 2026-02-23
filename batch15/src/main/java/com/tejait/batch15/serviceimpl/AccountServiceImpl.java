package com.tejait.batch15.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejait.batch15.Dto.AccountHolderDto;
import com.tejait.batch15.Dto.AccountResponseDto;
import com.tejait.batch15.model.Account;
import com.tejait.batch15.repository.AccountRepository;
import com.tejait.batch15.service.AccountService;

@Service
public class AccountServiceImpl  implements AccountService{
	
	@Autowired
	AccountRepository repository;

	@Override
	public Account saveAccount(Account acc) {
		
		acc.setCreatedDate(acc.getCreatedDate().now());
		
		acc.setUpdateDate(acc.getUpdateDate().now());
		
	
		
		return repository.save(acc); 
	}
// Here we are hidding the sensitive data of AccountDetails..... Using Dto...
	@Override
	public AccountResponseDto getbySensitiveAccDetails(Long id) {
		
		Account account=repository.findById(id).get();
		
		AccountResponseDto dto= new AccountResponseDto();
					dto.setAccountId(account.getAccountId());
					dto.setAccountType(account.getAccountType());
					dto.setHolderName(account.getHolderName());
					dto.setIfsc(account.getIfsc());
					dto.setKycStatus(account.getKycStatus());
	
		return dto;
	}
	
	// Here we are Showing required Details for Custmer....
	@Override
	public AccountHolderDto getByHolder(long id) {
		Account acc=repository.findById(id).get();
		
		AccountHolderDto Dto =new AccountHolderDto();
							Dto.setAccountId(acc.getAccountId());
							Dto.setAccountNum(acc.getAccountNum());
							Dto.setBalance(acc.getBalance());
			
		return Dto;
	}

	

	
	
	
}
