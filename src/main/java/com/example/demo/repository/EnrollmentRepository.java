package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    // List<Enrollment> findAllByCourse(Course course);
    // List<Enrollment> findAllByUserName(User user);
    // Enrollment findByCourseAndUserName(Course course, User user);
}
