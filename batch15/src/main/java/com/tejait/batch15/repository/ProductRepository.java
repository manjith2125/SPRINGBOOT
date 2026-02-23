package com.tejait.batch15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejait.batch15.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
	

	
	
	

}
