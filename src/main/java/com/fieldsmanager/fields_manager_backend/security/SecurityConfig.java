package com.fieldsmanager.fields_manager_backend.security;
//
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)


public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) { this.jwtFilter = jwtFilter; }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**" , "/error").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/fields/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/fields/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/fields/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/field-slots/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/field-slots/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/field-slots/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/field-slots/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/fields/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/enquiries/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/enquiries/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/enquiries/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }


}