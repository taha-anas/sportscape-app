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
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private Role role;
    private String email;
    private String password;
}
