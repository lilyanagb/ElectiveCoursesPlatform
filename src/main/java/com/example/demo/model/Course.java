package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getCredits() {
        return credits;
    }
}