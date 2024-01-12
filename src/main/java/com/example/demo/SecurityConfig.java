package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    
        @Bean
        public static PasswordEncoder passwordEncoder(){
             return new BCryptPasswordEncoder(8);
        }
        @Bean
        public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((authorize) ->
                            authorize.anyRequest().authenticated()
                            //TODO: Да има определени мапинги за студент и преподавател
                    ).formLogin(
                            form -> form
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .loginPage("/login")
                                    .loginProcessingUrl("/login")
                                    .defaultSuccessUrl("/home")
                                    .permitAll()
                    ).logout(
                            logout -> logout
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .permitAll()
                    );
            return http.build();
        }
}