package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transcation")
public class Transcation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer appId;

    private String transactionDate;
    private String activity;
    private String instrument;
    private String txnId;
    private String comment;

    private Double debtAmt;
    private Double creditAmt;

    private String transactionBreakup;
    private String transactionStatus;
}