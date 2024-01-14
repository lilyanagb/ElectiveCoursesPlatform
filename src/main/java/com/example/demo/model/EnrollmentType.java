package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment_type")
public class EnrollmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name; 
}