package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="company_address")
@Data
public class CompanyAddress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int appId;
    private String flatnum;
    private String building;
    private String line;
    private String state;
    private String city;
    private long pincode;
    private String landmark;
    private String area;

}
