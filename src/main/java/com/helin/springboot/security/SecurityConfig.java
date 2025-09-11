package com.helin.springboot.security;


import com.helin.springboot.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    private final JwtService jwtService;

    public static final String LOGIN =  "/api/users/login";
    public static final String REGISTER = "/api/users/register";
    public static final String ADMIN = "admin";
    public static final String USER = "ser";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request)
                        .requestMatchers(LOGIN, REGISTER, "/api/payment/initiate").permitAll()
                        .requestMatchers("/api/admin/register", "/api/admin/login").permitAll()
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN",ADMIN)
                        .requestMatchers("/api/user/**").hasAnyRole("USER", USER)
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCra))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authorizat)

    }

}
