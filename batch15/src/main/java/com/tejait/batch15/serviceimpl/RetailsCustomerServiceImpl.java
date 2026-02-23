package com.tejait.batch15.serviceimpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tejait.batch15.service.CustomerService;
//@Primary   // With This annotation we are saying this is Primary bean to be injected while using CustomerService since it has 2 child classes of Retails n Bussiness
@Service("retail")
public class RetailsCustomerServiceImpl implements CustomerService {

	@Override
	public String getCustomerType() {
		// TODO Auto-generated method stub
		return "Retail Customer";
	}

}
