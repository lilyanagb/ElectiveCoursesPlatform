package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    // List<Enrollment> findAllByCourse(Course course);
    // List<Enrollment> findAllByUserName(User user);
    // Enrollment findByCourseAndUserName(Course course, User user);
}
