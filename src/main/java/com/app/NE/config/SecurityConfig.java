package com.app.NE.config;

import com.app.NE.enums.ERole;
import com.app.NE.security.handlers.CustomAccessDeniedHandler;
import com.app.NE.security.handlers.JwtAuthenticationEntryPoint;
import com.app.NE.security.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configure(http));
        http.authorizeHttpRequests(req -> req
                .requestMatchers("/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/error",
                        "/swagger-resources/**",
                        "/api/v1/auth/login",
                        "/api/v1/chapters/**",
                        "/api/v1/auth/register",
                        "/actuator/**",
                        "/api-docs/**"
                        ).permitAll()
                // ADMIN && MANAGER AUTHORISED REQs
                .requestMatchers("/api/v1/employee/register").hasAnyRole("ADMIN", "MANAGER")
                // CUSTOMER AUTHORISED REQs
                .anyRequest().authenticated());

        http.exceptionHandling(
                exception -> exception.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        );
        http.exceptionHandling(
                exception -> exception.accessDeniedHandler(new CustomAccessDeniedHandler())
        );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
