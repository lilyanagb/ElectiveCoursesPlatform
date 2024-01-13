package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Course;
import com.example.demo.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    // Course findByCourseName(String name);
    // List<Course> findAllByTutor(Teacher teacher);
}
