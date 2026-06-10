package com.demo.gigforce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // ✅ Disable CSRF for APIs
                .csrf(csrf -> csrf.disable())

                // ✅ Enable CORS properly
                .cors(Customizer.withDefaults())

                // ✅ Stateless session (JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // ✅ Authorization rules
                .authorizeHttpRequests(auth -> auth

                        // ✅ Swagger (no login needed)
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ✅ Public APIs
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/timesheets/**").permitAll()
                        .requestMatchers("/api/invoice/**").permitAll()
                        .requestMatchers("/api/payment/**").permitAll()

                        // ✅ Users (ADMIN only)
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        // ✅ Contractors
                        .requestMatchers(HttpMethod.GET, "/api/contractors/**")
                        .hasAnyRole("CONTRACTOR", "HIRING_MANAGER", "VENDOR", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/contractors/**")
                        .hasAnyRole("ADMIN", "HIRING_MANAGER")

                        .requestMatchers(HttpMethod.PUT, "/api/contractors/**")
                        .hasAnyRole("ADMIN", "CONTRACTOR")

                        .requestMatchers(HttpMethod.DELETE, "/api/contractors/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/absences/**")
                        .hasRole("CONTRACTOR")

                        .requestMatchers(HttpMethod.GET, "/api/absences/**")
                        .hasAnyRole("ADMIN", "HIRING_MANAGER", "CONTRACTOR")

                        .requestMatchers(HttpMethod.PUT, "/api/absences/**")
                        .hasAnyRole("ADMIN", "HIRING_MANAGER")

                        // ✅ Certifications
                        .requestMatchers(HttpMethod.GET, "/api/certifications/**")
                        .hasAnyRole("CONTRACTOR", "HIRING_MANAGER", "VENDOR", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/certifications/**")
                        .hasAnyRole("CONTRACTOR", "ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/certifications/**")
                        .hasAnyRole("CONTRACTOR", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/certifications/**")
                        .hasRole("ADMIN")

                        // ✅ All remaining APIs require authentication
                        .anyRequest().authenticated()
                )

                // ✅ Disable default login
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())

                // ✅ Add JWT filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}