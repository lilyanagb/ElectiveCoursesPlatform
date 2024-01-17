package com.example.demo.util;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.repository.EnrollmentRepository;

public class Result {

    public Integer passedType1;
    public Integer passedType2;
    public Integer passedType3;
    public Integer passedType4;
    public Integer passedType5;

    public Integer credits;

    private final EnrollmentRepository enrollmentRepository;

    public Result(EnrollmentRepository enrollmentRepository) {
        
        this.enrollmentRepository = enrollmentRepository;
    
        this.passedType1 = 0;
        this.passedType2 = 0;
        this.passedType3 = 0;
        this.passedType4 = 0;
        this.passedType5 = 0;

        this.credits = 0;
    }

    public void init(User user) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByUserId(user.getId());
        
        for (Enrollment e : enrollments) {
            Course course = e.getCourse();
            if (course.getType().equals("ОКН")) {
                passedType1 ++;
            }
            if (course.getType().equals("ЯКН")) {
                passedType2 ++;
            }
            if (course.getType().equals("М")) {
                passedType3 ++;
            }
            if (course.getType().equals("ПМ")) {
                passedType4 ++;
            }
            if (course.getType().equals("КП")) {
                passedType5 ++;
            }

            credits += course.getCredits();
        }
    }
}
