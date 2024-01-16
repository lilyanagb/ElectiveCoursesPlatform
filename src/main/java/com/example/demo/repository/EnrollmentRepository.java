package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findAllByUserId(Integer user_id);
    List<Enrollment> findAllByUserIdAndCourseId(Integer userId, Integer courseId);
}
