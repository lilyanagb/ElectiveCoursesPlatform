package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public TeacherController(EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository,
                             RoleRepository roleRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/dashboard")
    public String teacherDashboard(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User currentUser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "").orElse(null);
        List<Enrollment> enrollments = enrollmentRepository.findAllByUserId(currentUser.getId());
        List<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            courses.add(enrollment.getCourse());
        }

        model.addAttribute("courses", courses);
        return "teacher/dashboard";
    }

    @GetMapping("/profile")
    public String showStudentProfile(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "").orElse(null);
        Role userRole = roleRepository.findById(user.getId()).orElse(null);
        
        model.addAttribute("user", user);
        model.addAttribute("userRole", userRole);
        
        return "teacher/profile";
    }
}