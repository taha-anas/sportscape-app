package com.sportscape.api.userservice.dto;

import com.sportscape.api.userservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private Role role;
}
