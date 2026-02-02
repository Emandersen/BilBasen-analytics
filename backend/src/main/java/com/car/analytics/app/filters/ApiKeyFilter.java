package com.car.analytics.app.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${API.KEY}")
    private String validApiKey;

    @Value("${ENVIRONMENT:production}")
    private String environment;

    private static final String API_HEADER = "X-API-KEY";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws java.io.IOException, ServletException {
        
        String apiKey = request.getHeader(API_HEADER);


        
        if (validApiKey.equals(apiKey)) {
            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(
                    "api-client",  
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_API"))
                );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication set: " + SecurityContextHolder.getContext().getAuthentication());
            filterChain.doFilter(request, response);
        } else {
            if ("development".equalsIgnoreCase(environment)) {
                System.out.println("Invalid API Key: " + apiKey);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Invalid API Key");
            } else {
                // In production, log invalid requests to files or monitoring systems !!!TO-DO!!!
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Bad Request");
            }
        return;
        }
    }
}