package com.sportscape.api.userservice.controller;

import com.sportscape.api.userservice.dto.RegisterRequest;
import com.sportscape.api.userservice.dto.UserRequest;
import com.sportscape.api.userservice.dto.UserResponse;
import com.sportscape.api.userservice.model.User;
//import com.sportscape.api.userservice.service.AuthService;
import com.sportscape.api.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
//@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {

        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(this::mapToUserResponse).collect(Collectors.toList()));

    }
    

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody RegisterRequest registerRequest) {
        User userData = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .phone(registerRequest.getPhone())
                .address(registerRequest.getAddress())
                .build();
        User newUser = userService.saveUser(userData);
        return new ResponseEntity<>(mapToUserResponse(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") long id) {
//        System.out.println("pass");
        Optional<User> user = userService.getUserById(id);
        return user
                .map(value -> new ResponseEntity<>(mapToUserResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserRequest userRequest ){
        if (!userService.userExistsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User userData = mapFromUserRequest(userRequest);
        userData.setId(id);
        User newUser = userService.updateUser(userData);
        return new ResponseEntity<>(mapToUserResponse(newUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        if (!userService.userExistsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }


    public User mapFromUserRequest(UserRequest userRequest) {
        return User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .role(userRequest.getRole())
                .build();
    }
//    @GetMapping("/CurrentUserInfo")
//    public ResponseEntity<UserResponse> getCurrentUserInfo() {
//        User user = authService.getCurrentUser();
//        if (user != null) {
//            UserResponse userResponse = UserResponse.builder()
//                    .id(user.getId())
//                    .firstname(user.getFirstname())
//                    .lastname(user.getLastname())
//                    .email(user.getEmail())
//                    .phone(user.getPhone())
//                    .address(user.getAddress())
//                    .role(user.getRole())
//                    .build();
//            return ResponseEntity.ok(userResponse);
//        } else {
//            return ResponseEntity.status(401).build();
//        }
//    }
}

