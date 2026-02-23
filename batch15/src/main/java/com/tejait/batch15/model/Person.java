package com.tejait.batch15.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.tejait.batch15.constants.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@RequiredArgsConstructor

@Data

@Table(name="person_b15")
@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;
	
	@Column(name="person_name" , nullable = false)
	private String name;
	
	@Column(name= "mail_id", nullable = false , unique = true)
	private String mail;
	
	private int age;
	
	@Transient
	private long balance;
	
	
//	@Pattern(regexcp = "^{A-Z}{5}{0-9}{4}{A-Z}{1}$")
	private String pan;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	 private LocalDateTime updatedAt;
	
	
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	
	
	
	
	

}
