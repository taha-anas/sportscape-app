package com.sportscape.api.userservice;

import com.sportscape.api.userservice.dto.RegisterRequest;
import com.sportscape.api.userservice.enums.Role;
//import com.sportscape.api.userservice.service.AuthService;
import com.sportscape.api.userservice.model.User;
import com.sportscape.api.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@EnableFeignClients(
        basePackages = "com.sportscape.api.clients.user"
)
public class UserServiceApplication {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args ->  {
            User admin = User.builder()
                    .firstname("admin")
                    .lastname("admin")
                    .email("admin@api.com")
                    .password("admin")
                    .role(Role.ADMIN)
                    .build();
//            String adminToken = authService.register(admin).getToken();

            User owner = User.builder()
                    .firstname("owner")
                    .lastname("owner")
                    .email("owner@api.com")
                    .password("owner")
                    .role(Role.OWNER)
                    .build();

            // saving users :
            userService.saveUser(admin);
            userService.saveUser(owner);

            log.info("Admin user: {}", admin);
            log.info("Owner user: {}", owner);
        };

    }
}
