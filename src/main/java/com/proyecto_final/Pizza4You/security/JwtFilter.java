package com.proyecto_final.Pizza4You.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyecto_final.Pizza4You.model.Rol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);

        try {
            final String userEmail = jwtService.extractUsername(jwt);
            final List<?> rolesRaw = jwtService.extractRoles(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                boolean isValid = jwtService.isTokenValid(jwt);

                if (isValid) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    
                    if (rolesRaw != null && !rolesRaw.isEmpty()) {
                        authorities = rolesRaw.stream()
                                .map(obj -> {
                                    String nombreRol = "";
                                    if (obj instanceof Map) {
                                        Map<?, ?> map = (Map<?, ?>) obj;
                                        nombreRol = (String) map.get("nombreCargo");
                                    } else if (obj instanceof Rol) {
                                        nombreRol = ((Rol) obj).getNombreCargo();
                                    }
                                    return new SimpleGrantedAuthority(nombreRol.startsWith("ROLE_") ? nombreRol : "ROLE_" + nombreRol);
                                })
                                .collect(Collectors.toList());
                    }

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userEmail,
                            null,
                            authorities
                    );
                    
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                   
                }
            }
            
        } catch (Exception e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}