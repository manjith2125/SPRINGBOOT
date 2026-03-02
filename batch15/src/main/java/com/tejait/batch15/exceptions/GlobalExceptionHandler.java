package com.tejait.batch15.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice  // Spring boot will recognize this is global exception handler class....
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IdNotFoundExceptions.class)
	public ResponseEntity<ErrorDtls> idNotFoundExceptio(HttpServletRequest request)  {
		
		Date date=new Date();
		
		ErrorDtls error=new ErrorDtls(date, 407, "Id Not found", "Given Id Not Available", request.getRequestURI());
		
		System.out.println(error);
		
		return new ResponseEntity<ErrorDtls> (error , HttpStatus.BAD_REQUEST);
		
		
		
	}
	
	// ==============================================================================================================================
	
    @ExceptionHandler(AccountAlreadyExists.class)
	public ResponseEntity<ErrorDtls> accountAlreadyExistException(HttpServletRequest request){
    	
    	
    	ErrorDtls error=new ErrorDtls(new Date(), 409, "Account Already Exist", "Account Already In Use", request.getRequestURI());
		return new ResponseEntity<ErrorDtls>(error, HttpStatus.BAD_REQUEST);
    	
	
	}
	
	// ==============================================================================================================================
	
    
	@ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorDtls> dataNotFoundException(HttpServletRequest request){
    	
		ErrorDtls error=new ErrorDtls(new Date(), 410, "Data Insufficient", "Data Not Found Enter Again", request.getRequestURI());
		
		
		return new ResponseEntity<ErrorDtls> (error, HttpStatus.ALREADY_REPORTED);
    }
	
	// ==============================================================================================================================
	
	@ExceptionHandler(InSufficientFundsException.class)
	public ResponseEntity<ErrorDtls> inSufficientFundsException(HttpServletRequest request){
		
		ErrorDtls error=new ErrorDtls(new Date(), 411, "InSufficient funds", "Founds InSufficient Funds Add Funds To Proceed", request.getRequestURI());
		return new ResponseEntity<ErrorDtls> (error , HttpStatus.ALREADY_REPORTED);
		
	}
	
	// ==============================================================================================================================
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDtls> globalExceptionMethod(HttpServletRequest request){
		ErrorDtls error=new ErrorDtls(new Date(), 501, "Something went wrong", "Unknown error occured in the code ", request.getRequestURI());
		
		return new ResponseEntity<ErrorDtls> (error, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@ExceptionHandler(MailAlreadyExists.class)
	public ResponseEntity<ErrorDtls> MailAlreadyExists(MailAlreadyExists ex,HttpServletRequest request){

		ErrorDtls error=new ErrorDtls(new Date(), 420, "MailAlreadyExists", ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<> (error , HttpStatus.ALREADY_REPORTED);

	}

	@ExceptionHandler(MobileNumberAlreadyExists.class)
	public ResponseEntity<ErrorDtls> MobileNumberAlreadyExists(MobileNumberAlreadyExists ex,HttpServletRequest request){

		ErrorDtls error=new ErrorDtls(new Date(), 421, "MobileNumberAlreadyExists", ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorDtls> (error , HttpStatus.ALREADY_REPORTED);

	}





}
