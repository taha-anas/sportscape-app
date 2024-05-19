package com.sportscapeapi.userservice.controller;

import com.sportscapeapi.userservice.dto.AuthRequest;
import com.sportscapeapi.userservice.dto.AuthResponse;
import com.sportscapeapi.userservice.dto.RegisterRequest;
import com.sportscapeapi.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
