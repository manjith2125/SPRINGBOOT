package com.tejait.batch15.serviceimpl;

import org.springframework.stereotype.Service;

import com.tejait.batch15.model.Products;
import com.tejait.batch15.repository.ProductRepository;
import com.tejait.batch15.service.ProductService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductServiceImpl  implements ProductService{

	
	
	ProductRepository repository;
	@Override
	public Products saveProducts(Products products) {
		
		return repository.save(products);
	}

}
