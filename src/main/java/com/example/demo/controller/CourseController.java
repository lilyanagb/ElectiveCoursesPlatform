package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.EnrollmentTypeRepository;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentTypeRepository enrollmentTypeRepository;

    public CourseController(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository,
                                UserRepository userRepository, EnrollmentTypeRepository enrollmentTypeRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.enrollmentTypeRepository = enrollmentTypeRepository;
    }

    @GetMapping("/")
    public String allCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());

        return "course/allcourses";
    }

    @GetMapping("/form")
    public String showAddCourseForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("teacher"))) {
         return "course/form";
        } else {
            return "redirect:/dashboard";
        }
    }

    @PostMapping("/form")
    public String addCourse(@ModelAttribute Course course) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("teacher"))) {
            Course savedCourse = courseRepository.save(course);
            User currentUser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "")
                                .orElseThrow(() -> new RuntimeException("User not found"));

            Enrollment enrollment = new Enrollment();
            enrollment.setUser(currentUser);
            enrollment.setCourse(savedCourse);
            enrollment.setEnrollmentType(enrollmentTypeRepository.findByName("teacher"));
            
            enrollmentRepository.save(enrollment);
        } 
        
        return "redirect:/dashboard";
    }
}
