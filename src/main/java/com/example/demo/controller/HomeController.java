package com.example.demo.controller;

// import java.io.IOException;
// import java.util.Collection;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.stereotype.Controller;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Controller
// public class HomeController implements AuthenticationSuccessHandler{

//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//         if (authorities.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
//             response.sendRedirect("/student/dashboard");
//         } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
//             response.sendRedirect("/teacher/dashboard");
//         } else {
//             response.sendRedirect("/access-denied"); // Обработка на грешка
//         }
//     }
// }