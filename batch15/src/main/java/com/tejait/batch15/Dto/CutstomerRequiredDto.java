package com.tejait.batch15.Dto;

import java.util.List;

import com.tejait.batch15.model.Orders;

import lombok.Data;

@Data
public class CutstomerRequiredDto {
	
	private int customerId;
	private List<Orders> orders;
	private List<Integer> prodIds;


}
