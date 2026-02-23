package com.tejait.batch15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.Dto.AccountHolderDto;
import com.tejait.batch15.Dto.AccountResponseDto;
import com.tejait.batch15.model.Account;
import com.tejait.batch15.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService service;
	
	
	@PostMapping("/saveAccount")
	public ResponseEntity<Account> saveAcoount(@RequestBody Account acc){
		
		Account savedAccount=service.saveAccount(acc);
		
		return new ResponseEntity<Account> (savedAccount,HttpStatus.CREATED);
		
		
	}
	
	// Here we are hidding the sensitive data of AccountDetails..... Using Dto...
	
	@GetMapping("getByCustomerAcc/{id}")
    public ResponseEntity<AccountResponseDto> getByCustomerAcc(@PathVariable Long id){
		
		AccountResponseDto dto=service.getbySensitiveAccDetails(id);
	
	return new ResponseEntity<AccountResponseDto>(dto, HttpStatus.OK);
			
		
		
	
}
	@GetMapping("customerOwnDetails/{id}")
	public ResponseEntity<AccountHolderDto> getbyHolderAcc(@PathVariable long id){
		
		AccountHolderDto HolderDto=service.getByHolder(id);
		return new ResponseEntity<AccountHolderDto>(HolderDto , HttpStatus.OK);
		
	}
	
	
	
} 
