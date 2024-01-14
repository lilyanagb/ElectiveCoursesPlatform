package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public StudentController(EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String studentDashboard(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User currentUser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "").orElse(null);
        List<Enrollment> enrollments = enrollmentRepository.findAllByUserId(currentUser.getId());
        List<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            courses.add(enrollment.getCourse());
        }

        model.addAttribute("courses", courses);

        return "student/dashboard";
    }
}