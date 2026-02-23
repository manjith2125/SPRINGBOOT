package com.tejait.batch15.serviceimpl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import com.tejait.batch15.exceptions.IdNotFoundExceptions;
import com.tejait.batch15.exceptions.InSufficientFundsException;
import com.tejait.batch15.model.Payment;
import com.tejait.batch15.repository.PaymentRespository;
import com.tejait.batch15.service.PaymentService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PaymentServiceImpl  implements PaymentService{
	
	PaymentRespository repository;
	
	private static final Logger logger=LogManager.getLogger(PaymentServiceImpl.class);
	
	@Override
	//@Transactional(noRollbackFor = RuntimeException.class)
	public Payment savePayment(Payment payment) {
		
		logger.debug("Entered into payment Service");
		logger.info("Payment Data :{}", payment);
	
		
		//Failure scenario
		if(payment.getAmount()<1) {
			
			
			logger.info("Transcation Amount :{}", payment.getAmount());
			
			
		
			payment.setTransactionId(UUID.randomUUID().toString());
			payment.setPaymentStatus("Failed");
			
			logger.warn("Payment Status :{}", payment.getPaymentStatus());
			
			logger.error("Invalid Amount: {}", payment.getAmount());
		
			
			throw new InSufficientFundsException("Not a Valid Amount");
		}
		
		// Fraud Alert
		if(payment.getAmount()>100000) {
			
			logger.warn("Fraud Alert :{}, Alert Higher Amount Transaction :{}", payment.getPayerName(),payment.getAmount());
		}
		try {
			payment.setTransactionId(UUID.randomUUID().toString());
			payment.setPaymentStatus("Success");
			return repository.save(payment);
		}catch (Exception e) {
			logger.error("Error While Making Payment");
			
			throw e;
		}
			
	}
		
		
	

	@Override
	public Payment getByPaymentId(Integer paymentId) {
  return repository.findById(paymentId).orElseThrow(()->new IdNotFoundExceptions("Invalid user Id"));
		
	}

	@Override
	public Payment getRefundByPaymentId(Integer paymentId) {
	Payment payment=repository.findById(paymentId)
				.orElseThrow(()-> {
					logger.error("Payment ID NOT FOUND :{}", paymentId);	
					
						return new IllegalArgumentException("Payment Not Found");
				});
	
	if(!payment.getPaymentStatus().equalsIgnoreCase("Success")) {
		
		logger.debug("Payment Status is Not Success");
		logger.debug("Payment Not Allowed For Refund");
		
		throw new IllegalArgumentException("Payment NOT ALLOWED FOR REFUND");
		
	}
	
		payment.setPaymentStatus("Refund");
		payment.setTransactionId(UUID.randomUUID().toString());
		
		
		
		return repository.save(payment);
	}

}
