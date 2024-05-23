//package com.sportscape.api.userservice.service;
//
//import com.sportscape.api.userservice.dto.RegisterRequest;
//import com.sportscape.api.userservice.repository.UserRepository;
//import com.sportscape.api.userservice.dto.AuthRequest;
//import com.sportscape.api.userservice.dto.AuthResponse;
//import com.sportscape.api.userservice.model.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthResponse register(RegisterRequest request) {
//
//        User user = User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRole())
//                .build();
//
//        userRepository.save(user);
//
//        String jwtToken = jwtService.generateToken(user);
//
//        return AuthResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    public AuthResponse authenticate(AuthRequest request) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + request.getEmail()));
//
//
//        String jwtToken = jwtService.generateToken(user);
//
//        return AuthResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//    public User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//
//            if (principal instanceof UserDetails userDetails) {
//                String email = userDetails.getUsername();
//                return userRepository.findByEmail(email)
//                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//            }
//        }
//        return null;
//    }
//}
//
//
