package com.sportscapeapi.userservice;

import com.sportscapeapi.userservice.dto.RegisterRequest;
import com.sportscapeapi.userservice.enums.Role;
import com.sportscapeapi.userservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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

            RegisterRequest seller = RegisterRequest.builder()
                    .firstname("seller")
                    .lastname("seller")
                    .email("seller@api.com")
                    .password("seller")
                    .role(Role.SELLER)
                    .build();
            String sellerToken = authService.register(seller).getToken();
            log.info("Admin token: {}", adminToken);
            log.info("Seller token: {}", sellerToken);
        };

    }
}
