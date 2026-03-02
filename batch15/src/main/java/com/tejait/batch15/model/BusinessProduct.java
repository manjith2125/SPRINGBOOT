package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="business_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private int appId;
    private String purposeOfLoan;
    private String natureOfBusiness;
    private int tenure;
    private long loanAmount;



}
