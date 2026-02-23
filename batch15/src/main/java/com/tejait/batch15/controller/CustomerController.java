package com.tejait.batch15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.Dto.CustomerProductsRequestDto;
import com.tejait.batch15.model.Customer;
import com.tejait.batch15.model.Pan;
import com.tejait.batch15.service.CustomerDataService;



import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/customer")

public class CustomerController {
	
	
	CustomerDataService service;
	// ------------------------------------------------SAVE CUSTOMER---------------------------------------------------------
	@PostMapping("/customer")
	public ResponseEntity<Customer> saveCustomerPan(@RequestBody Customer customer){
		
		Customer savedCustomerPan=service.saveCustomerPan(customer);
		
		return new ResponseEntity<>(savedCustomerPan ,HttpStatus.CREATED);
		
	}
	// ---------------------------------DELETE CUSTOMER PAN ----------------------------------------------------------------------------
	@DeleteMapping("DeletePanOfCustomer/{panid}")
	public ResponseEntity<String> deleteCustomerPan(@PathVariable Integer panid){
		
		service.deleteCustomerPan(panid);
		
		return new ResponseEntity<String>("Deleted customer Pan", HttpStatus.OK);
		
		
		
	}
	// ----------------------------------GET -ALL -CUSTOMER   ------------------------------------------------------------------------
	@GetMapping("GetAllCustomer")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		List<Customer> list=service.getAllCustomer();
		return new  ResponseEntity<>(list, HttpStatus.OK);
	
	}
	
	
	
	

// -----------------------------------mapproductsToCustomer------------------------------------------------------------------
	@PostMapping("mapproductsToCustomer")
	public ResponseEntity<Customer> mapProductsToCustomer(@RequestBody CustomerProductsRequestDto request){
		
		Customer customer=service.mapProductsToCustomer(request);
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	
	//--------------------------------------getById OF CUSTOMER------------------------------------------------------------------------------------
	@GetMapping("getById/{id}")
	public ResponseEntity<Customer> AllDetails(@PathVariable Integer id){
	Optional<Customer> result=service.AllDetails(id);
		return new ResponseEntity<>(result.get(), HttpStatus.OK);
		
	}
	
	
}
