package com.tejait.batch15.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="pan_b15")
@Entity
public class Pan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	
	
	@Column (unique = true)
	private String pnNumber;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pn_fk_id") // Foreign key...
	private Customer cust;
	
	
	
	
	
}
