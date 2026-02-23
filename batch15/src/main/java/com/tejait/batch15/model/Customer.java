package com.tejait.batch15.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Table(name="customer_b15")
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String name;
	private int age;
	private long mobile;
	
	@OneToOne(mappedBy = "cust" , cascade = CascadeType.ALL)
	private Pan pan;
	
	
	@OneToMany( cascade = CascadeType.ALL)
	private List<Orders> orders;
	// ----------------------------------Many To Many-----------------------------------------------------------
	@ManyToMany
	@JoinTable(name="customer_products",
			
			joinColumns = @JoinColumn(name="cid"),
			inverseJoinColumns = @JoinColumn(name="prodId"))
	
	private List<Products> products;
	//--------------------------------------------------------------------------------------------------------------
	

	
	
	
	

}
