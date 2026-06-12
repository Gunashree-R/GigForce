package com.demo.gigforce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    // Constructor Injection (no Lombok)
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // Public APIs

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        .requestMatchers("/api/auth/**").permitAll()

                        
                         // TIMESHEET

                        // Contractor → create
                        .requestMatchers(HttpMethod.POST, "/api/timesheets/**")
                        .hasAuthority("CONTRACTOR")
                    
                        // View
                        .requestMatchers(HttpMethod.GET, "/api/timesheets/**")
                        .hasAnyAuthority("CONTRACTOR", "HIRING_MANAGER", "ADMIN")
                    
                        // Submit / Approve
                        .requestMatchers(HttpMethod.PUT, "/api/timesheets/**")
                        .hasAnyAuthority("HIRING_MANAGER")

                        //invoice
                        .requestMatchers("/api/invoice/**").permitAll()
                        .requestMatchers("/api/payment/**").permitAll()

                        // Users
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        //Contractors
                        .requestMatchers(HttpMethod.GET, "/api/contractors/**")
                        .hasAnyRole("CONTRACTOR", "HIRING_MANAGER", "VENDOR", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/contractors/**")
                        .hasAnyRole("ADMIN", "HIRING_MANAGER")

                        .requestMatchers(HttpMethod.PUT, "/api/contractors/**")
                        .hasAnyRole("ADMIN", "CONTRACTOR")

                        .requestMatchers(HttpMethod.DELETE, "/api/contractors/**")
                        .hasRole("ADMIN")
                        // absence
                        .requestMatchers(HttpMethod.POST, "/api/absences/**")
                        .hasRole("CONTRACTOR")

                        .requestMatchers(HttpMethod.GET, "/api/absences/**")
                        .hasAnyRole("ADMIN", "HIRING_MANAGER", "CONTRACTOR")

                        .requestMatchers(HttpMethod.PUT, "/api/absences/**")
                        .hasAnyRole("ADMIN", "HIRING_MANAGER")
                        // Certifications
                        .requestMatchers(HttpMethod.GET, "/api/certifications/**")
                        .hasAnyRole("CONTRACTOR", "HIRING_MANAGER", "VENDOR", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/certifications/**")
                        .hasAnyRole("CONTRACTOR", "ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/certifications/**")
                        .hasAnyRole("CONTRACTOR", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/certifications/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )


                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())


                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
