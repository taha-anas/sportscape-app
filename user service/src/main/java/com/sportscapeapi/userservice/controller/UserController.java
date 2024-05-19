package com.sportscapeapi.userservice.controller;

import com.sportscapeapi.userservice.dto.UserRequest;
import com.sportscapeapi.userservice.dto.UserResponse;
import com.sportscapeapi.userservice.model.User;
import com.sportscapeapi.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
//@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(this::mapToUserResponse).toList());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User userData = mapFromUserRequest(userRequest);
        User newUser = userService.saveUser(userData);
        return new ResponseEntity<>(mapToUserResponse(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
        Optional<User> user = userService.getUserById(id);
        return user
                .map(value -> new ResponseEntity<>(mapToUserResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest ){
        if (!userService.userExistsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User userData = mapFromUserRequest(userRequest);
        userData.setId(id);
        User newUser = userService.updateUser(userData);
        return new ResponseEntity<>(mapToUserResponse(newUser), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
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
}
