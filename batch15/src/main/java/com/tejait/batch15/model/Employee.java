package com.tejait.batch15.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter


@Data
@Table  (name="employee_b15")							// with this we can change the table name to out coustum name ...
@Entity // This will consider it as entity for jdbc..
public class Employee {
	
	@Id                  // Primary key ...
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // For Auto_Incremenet..
	
	private int id;
	
	//@Column(name="first_name")
	private String fname;
	private String lname;
	private String fullname;
	private String dept;
	private Integer age;
	private long salary;
	private String empCode;
	
	
	
	
	

}
