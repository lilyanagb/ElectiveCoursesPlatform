package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        // Получаване на текущия потребител от аутентикационния обект
        //String username = authentication.getName();
        
        // Тук можете да извлечете ролята на потребителя от базата данни и да определите редиректа
        // Пример: String role = userService.getUserRole(username);
        
        // За целите на примера, ще използваме хардкодната информация за ролята
        String role = "ROLE_TEACHER"; // Пример

        if (role.equals("ROLE_TEACHER")) {
            return "redirect:/teacher/dashboard";
        } else if (role.equals("ROLE_STUDENT")) {
            return "redirect:/student/dashboard";
        } else {
            return "redirect:/access-denied"; // Обработка на грешка
        }
    }

    @GetMapping("/teacher/dashboard")
    public String teacherDashboard() {
        return "teacher_dashboard";
    }

    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "student_dashboard";
    }
}