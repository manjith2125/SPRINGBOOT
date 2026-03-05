package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_report")
public class SalesReportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer appId;

    private String date;

    // Matches {{record.orderno}}
    private String orderno;

    // Matches {{record.invoiceno}}
    private String invoiceno;

    private String partyName;

    // Matches {{record.partyPhoneNum}}
    private String partyPhoneNum;

    private Double totalAmount;

    // Matches {{record.recievedOrPaidAmount}}
    private Double recievedOrPaidAmount;

    private Double balanceAmount;
}
