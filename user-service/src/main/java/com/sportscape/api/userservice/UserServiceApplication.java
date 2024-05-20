package com.sportscape.api.userservice;

import com.sportscape.api.userservice.dto.RegisterRequest;
import com.sportscape.api.userservice.enums.Role;
import com.sportscape.api.userservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthService authService) {
        return args ->  {
            RegisterRequest admin = RegisterRequest.builder()
                    .firstname("admin")
                    .lastname("admin")
                    .email("admin@api.com")
                    .password("admin")
                    .role(Role.ADMIN)
                    .build();
            String adminToken = authService.register(admin).getToken();

            RegisterRequest owner = RegisterRequest.builder()
                    .firstname("owner")
                    .lastname("owner")
                    .email("owner@api.com")
                    .password("owner")
                    .role(Role.OWNER)
                    .build();
            String ownerToken = authService.register(owner).getToken();
            log.info("Admin token: {}", adminToken);
            log.info("Owner token: {}", ownerToken);
        };

    }
}
