package com.tejait.batch15.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tejait.batch15.Dto.CustomerProductsRequestDto;
import com.tejait.batch15.model.Customer;
import com.tejait.batch15.model.Pan;

@Service
public interface CustomerDataService {

	Customer saveCustomerPan(Customer customer);

	Customer mapProductsToCustomer(CustomerProductsRequestDto request);

	

	Optional<Customer> AllDetails(Integer id);

	void deleteCustomerPan(Integer panid);

	List<Customer> getAllCustomer();

	



}
