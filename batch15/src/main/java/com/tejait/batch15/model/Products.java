package com.tejait.batch15.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "products_b15")
@Entity
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodId;
	
	private String productName;
	private int price;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Customer> customers;
	

}
