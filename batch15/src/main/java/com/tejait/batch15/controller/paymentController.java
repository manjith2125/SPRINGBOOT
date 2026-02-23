package com.tejait.batch15.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.model.Payment;
import com.tejait.batch15.service.PaymentService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("payment")
public class paymentController {
	
	private static final Logger logger=LogManager.getLogger(paymentController.class);
	
	PaymentService service;
	
	@PostMapping("makePayment")
	public ResponseEntity<Payment> makePayment(@RequestBody Payment payment){
		logger.debug("Entered Into Payment Controller");
		logger.info("Payment Data from User :{}", payment);
		
		Payment savedPayment=service.savePayment(payment);
		
		logger.info("Saved Payment Data : {}", savedPayment);
		return new ResponseEntity<>(savedPayment, HttpStatus.OK);
		
	}
	@GetMapping("byPaymentId/{paymentId}")
	public ResponseEntity<Payment> getPaymentBypaymentId(@PathVariable Integer paymentId){
		
		logger.debug("Entered into getPaymentBypaymentId");
	logger.info("PaymentId :{}", paymentId);
	
		Payment payment=service.getByPaymentId(paymentId);
		return new ResponseEntity<>(payment, HttpStatus.OK);
		
		
	}
	
	@GetMapping("refunfByPaymentId/{paymentId}")
	public ResponseEntity<Payment> getRefundByPaymentId(@PathVariable Integer paymentId){
		logger.debug("Entered into GetRefundByPaymentId");
		
		logger.info("PaymentId :{}", paymentId);
		
		Payment payment=service.getRefundByPaymentId(paymentId);
		return new ResponseEntity<>(payment, HttpStatus.OK);
		
	}
	

}
