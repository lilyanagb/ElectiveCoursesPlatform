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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.EnrollmentTypeRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
    public String allCourses(Model model, @RequestParam(required = false) String error ) {
        model.addAttribute("courses", courseRepository.findAll());
        
        if (error != null && error.equals("true")) {
            model.addAttribute("alreadyEnrolled", true);
        }
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

    @PostMapping("/enrollcourse")
    public String enrollStudent(@RequestParam("id") Integer courseId, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "").orElse(null);

        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();

            if (isEnrolled(currentUser, course)) {
                return "redirect:/course/?error=true";
            }

            Enrollment enrollment = new Enrollment();
            enrollment.setUser(currentUser);
            enrollment.setCourse(course);
            enrollment.setEnrollmentType(enrollmentTypeRepository.findByName("student"));
            
            enrollmentRepository.save(enrollment);
        }
        
        return "redirect:/course/";
    } 

    @PostMapping("/removefromcourse")
    public String removeStudentFromCourse(@RequestParam("id") Integer courseId, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "").orElse(null);

        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isPresent()) {
            Enrollment enrollment = enrollmentRepository.findAllByUserIdAndCourseId(currentUser.getId(), courseId).get(0);
            enrollmentRepository.deleteById(enrollment.getId());
        }
        
        return "redirect:/dashboard";
    } 

    private boolean isEnrolled(User student, Course course) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByUserIdAndCourseId(student.getId(), course.getId());
        return !enrollments.isEmpty();
    }
}
