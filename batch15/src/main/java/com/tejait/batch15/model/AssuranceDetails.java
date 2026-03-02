package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assurance_details")
public class AssuranceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer appId;

    private String ename;
    private String nationality;
    private int age;
    private String mail;
    private String gender;
}