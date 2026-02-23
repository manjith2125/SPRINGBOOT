package com.tejait.batch15.Dto;

import java.util.List;

import lombok.Data;

@Data

public class CustomerProductsRequestDto {
	
	private int customerId;
	
	private List<Integer> prodIds;
	

}
