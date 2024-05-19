package com.sportscapeapi.userservice.dto;

import com.sportscapeapi.userservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private Role role;
}