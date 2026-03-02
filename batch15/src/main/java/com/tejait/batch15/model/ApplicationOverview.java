package com.tejait.batch15.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ApplicationOverview {


    private  int appId;
    private String companyName;
    private String city;
    private long mobile;
    private String mail;
    private long loanAmt;
    private int tenure;
    private String companyPan;


}
