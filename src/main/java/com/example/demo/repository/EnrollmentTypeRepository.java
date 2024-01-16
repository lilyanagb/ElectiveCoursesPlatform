package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EnrollmentType;

@Repository
public interface EnrollmentTypeRepository extends JpaRepository<EnrollmentType, Integer> {
    EnrollmentType findByName(String name);
} 
