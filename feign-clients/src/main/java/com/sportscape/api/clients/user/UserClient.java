package com.sportscape.api.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "user",
        path = "api/users"
)
public interface UserClient {
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable long id);
}
