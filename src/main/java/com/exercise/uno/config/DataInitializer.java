package com.exercise.uno.config;

import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
//
//    @Bean
//    public CommandLineRunner initData(UserRepository userRepo, PasswordEncoder encoder){
//        return args -> {
//            User admin = new User();
//            admin.setUsername("admin");
//            admin.setPassword("admin");
//            admin.setRole("ADMIN");
//            userRepo.save(admin);
//
//            User user = new User();
//            user.setUsername("user");
//            user.setPassword("user");
//            user.setRole("USER");
//            userRepo.save(user);
//        };
//    }

}
