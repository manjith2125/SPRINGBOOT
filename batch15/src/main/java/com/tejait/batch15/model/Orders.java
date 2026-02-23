package com.tejait.batch15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data

@Table(name="orderb_15")

@Entity
public class Orders {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oid;
	private String orderName;
	private String orderStatus;
	
	//----------------------------------------------------------------------------------------------------------------
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ord_fk_id")
	
	private Customer custom;
	

	
	
	
}
