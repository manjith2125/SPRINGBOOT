package com.tejait.batch15.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tejait.batch15.Dto.OrdersRequestDto;
import com.tejait.batch15.exceptions.DataNotFoundException;
import com.tejait.batch15.model.Customer;
import com.tejait.batch15.model.Orders;
import com.tejait.batch15.repository.CustomerRepository;
import com.tejait.batch15.repository.OrderRepository;
import com.tejait.batch15.service.OrderService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class OrderServiceImpl  implements OrderService{

	
	OrderRepository repository;
	
	CustomerRepository customerRepository;
	

	@Override
	public OrdersRequestDto saveOrders(OrdersRequestDto dto) 
	
	
	{
	
		Customer customer=customerRepository.findById(dto.getCustomerId())
																.orElseThrow(() -> new DataNotFoundException("Customer not Found"));
		
		
		List<Orders> ordersList=dto.getOrders(); // mobile, clothes , Toys
		for(Orders order:ordersList) {
			
			order.setCustom(customer); // Manjith -- koreign key....
		}
		//dto.getOrders().forEach(Orders -> Orders.setCustom(customer));
		
		List<Orders> savedOrdersList=repository.saveAll(ordersList);
		OrdersRequestDto responseDto=new OrdersRequestDto();
		responseDto.setOrders(savedOrdersList);
		responseDto.setCustomerId(dto.getCustomerId());
	
		return responseDto;
	}

}
