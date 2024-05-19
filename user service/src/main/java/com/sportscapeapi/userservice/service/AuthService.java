package com.sportscapeapi.userservice.service;


import com.sportscapeapi.userservice.dto.AuthRequest;
import com.sportscapeapi.userservice.dto.AuthResponse;
import com.sportscapeapi.userservice.dto.RegisterRequest;
import com.sportscapeapi.userservice.model.User;
import com.sportscapeapi.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // first of all create a user with the user details in the request body
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        // save the user to the userRepo
        userRepository.save(user);
        // generate a token using JwtService
        var jwtToken = jwtService.generateToken(user);
        // return the response body
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        // call the authentication manager to check the validation of username and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        // if the authentication success then create a find the user details
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        // generate a token using JwtService
        var jwtToken = jwtService.generateToken(user);
        // return the response body
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
