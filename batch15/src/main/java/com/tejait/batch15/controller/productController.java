package com.tejait.batch15.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.model.Products;
import com.tejait.batch15.service.ProductService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("products")
public class productController {
	
	ProductService service;
	
	
	@PostMapping("save")
	public ResponseEntity<Products> saveProducts(@RequestBody Products products){
		
		Products savedProducts=service.saveProducts(products);
		
		return new ResponseEntity<Products>(savedProducts, HttpStatus.CREATED);
		
	}
	

}
