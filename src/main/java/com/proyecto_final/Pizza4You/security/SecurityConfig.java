package com.proyecto_final.Pizza4You.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity  
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	    // Public: auth (login/register)
            	    .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
            	    
            	    // Public: read-only product/catalog data (GET)
            	    .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/api/ingredientes/disponibles").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/tamanos").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/masas").permitAll()
            	    
            	    // Public: static resources
            	    .requestMatchers("/imagenes/**", "/static/**").permitAll()
            	    
            	    // Public: Swagger/OpenAPI docs
            	    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
            	    
            	    // Public: error page
            	    .requestMatchers("/error").permitAll()
            	    
            	    // Everything else 
            	    .anyRequest().authenticated()
            	)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}