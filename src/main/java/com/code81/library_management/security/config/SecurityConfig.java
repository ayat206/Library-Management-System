package com.code81.library_management.security.config;

import com.code81.library_management.logic.service_impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder encoder, CustomUserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz

                        // Public (Unauthenticated)
                        .requestMatchers("/api/auth/**").permitAll()

                        // Admin only
                        .requestMatchers("/api/users/**").hasAuthority("Administrator")
                        .requestMatchers("/api/roles/**").hasAuthority("Administrator")
                        .requestMatchers("/api/logs/**").hasAuthority("Administrator")

                        // Librarian + Admin
                        .requestMatchers("/api/books/**").hasAnyAuthority("Administrator", "Librarian")
                        .requestMatchers("/api/members/**").hasAnyAuthority("Administrator", "Librarian")
                        .requestMatchers("/api/publishers/**").hasAnyAuthority("Administrator", "Librarian")
                        .requestMatchers("/api/authors/**").hasAnyAuthority("Administrator", "Librarian")
                        .requestMatchers("/api/categories/**").hasAnyAuthority("Administrator", "Librarian")


                        // Staff + Librarian + Admin
                        .requestMatchers("/api/transactions/**").hasAnyAuthority("Administrator", "Librarian", "Staff")

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }



}
