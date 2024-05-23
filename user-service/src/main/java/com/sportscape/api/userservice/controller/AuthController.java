//package com.sportscape.api.userservice.controller;
//
//import com.sportscape.api.userservice.dto.AuthRequest;
//import com.sportscape.api.userservice.dto.AuthResponse;
//import com.sportscape.api.userservice.dto.RegisterRequest;
//import com.sportscape.api.userservice.model.User;
//import com.sportscape.api.userservice.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/auth")
//public class AuthController {
//    private final AuthService authService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<AuthResponse> register(
//            @RequestBody RegisterRequest request
//    ){
//        return ResponseEntity.ok(authService.register(request));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(
//            @RequestBody AuthRequest request
//    ){
//        return ResponseEntity.ok(authService.authenticate(request));
//    }
//
//
//
//    @GetMapping("/CurrentLoggedIn")
//    public ResponseEntity<User> getCurrentLoggedIn() {
//        User user = authService.getCurrentUser();
//
//        if (user != null) {
//
//            return ResponseEntity.ok(user);
//        } else {
//
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//
//}
