package com.tejait.batch15.serviceimpl;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tejait.batch15.Dto.CustomerProductsRequestDto;
import com.tejait.batch15.exceptions.DataNotFoundException;
import com.tejait.batch15.model.Customer;
import com.tejait.batch15.model.Pan;
import com.tejait.batch15.model.Products;
import com.tejait.batch15.repository.CustomerRepository;
import com.tejait.batch15.repository.ProductRepository;
import com.tejait.batch15.service.CustomerDataService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl  implements CustomerDataService{
	
	
	CustomerRepository repository;
	
	ProductRepository productRepository;
// ---------------------------------------------------------- saveCustomerPan---------------------------------------
	@Override
	public Customer saveCustomerPan(Customer customer) {
		if(customer.getPan()!=null) {
			
			customer.getPan().setCust(customer);
		}
		

		return repository.save(customer);
	}
//=======================================mapProductsToCustomer=======================================================
	@Override
	public Customer mapProductsToCustomer(CustomerProductsRequestDto request) {
	Customer customer=repository.findById(request.getCustomerId())
					.orElseThrow(()-> new DataNotFoundException("customer not found"));
	List<Products> productsList=productRepository.findAllById(request.getProdIds());
	customer.getProducts().addAll(productsList);
	Customer saved=repository.save(customer);
	
	
		return saved;
	}
//-----------------------------------------------------------FindById ------------------------------------------------
	@Override
	public Optional<Customer> AllDetails(Integer id) {
		
		return repository.findById(id);
	}
// -------------------------------------------------------deleteCustomerPan------------------------------------------------
	@Override
	public void deleteCustomerPan(Integer panid) {
	
	 repository.deleteById(panid);
	}
//-------------------------------------------------------getAllByCustomers--------------------------------------
	@Override
	public List<Customer> getAllCustomer() {
		
		return repository.findAll();
	}
	
	

	

	

	

}
