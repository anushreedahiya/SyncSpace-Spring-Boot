package com.syncspace.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/actuator/**", "/api/users/**", "/api/auth/**").permitAll()
                        // .requestMatchers("/api/tasks/**", "/api/gemini/**").authenticated()
                    .anyRequest().permitAll()
                );
        //         .httpBasic(Customizer.withDefaults());
        // http.addFilterBefore(new FirebaseJwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

