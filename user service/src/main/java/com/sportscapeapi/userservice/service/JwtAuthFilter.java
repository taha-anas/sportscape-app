package com.sportscapeapi.userservice.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authHeader.substring(7);
        String username =   jwtService.extractUsername(jwt);
        // check if the username not null and the authContext is null wish means the user not authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // retrieve userDetails
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // check if the token is valid
            if (jwtService.validateToken(jwt, userDetails)) {
                //initialize the auth token with the userDetails and authorities
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                // add the request body to the auth
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //update the context holder with the authToken
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // otherwise do filter
        filterChain.doFilter(request, response);
    }
}
