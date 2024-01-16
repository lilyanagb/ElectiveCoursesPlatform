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
import com.example.demo.model.Role;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public StudentController(EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository, 
                             RoleRepository roleRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    @GetMapping("/profile")
    public String showStudentProfile(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "").orElse(null);
        Role userRole = roleRepository.findById(user.getId()).orElse(null);
        
        model.addAttribute("user", user);
        model.addAttribute("userRole", userRole);
        
        return "student/profile";
    }
}