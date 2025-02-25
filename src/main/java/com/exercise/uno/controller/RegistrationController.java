package com.exercise.uno.controller;

import com.exercise.uno.modules.dto.DTOConverter;
import com.exercise.uno.modules.dto.UserDTO;
import com.exercise.uno.modules.entity.User;
import com.exercise.uno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register/")
public class RegistrationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private DTOConverter dtoConverter;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDtoReq) {
        if(userRepository.existsByUsername(userDtoReq.getUsername())){
            return ResponseEntity.badRequest().body("Username already exist");
        }
        User user = dtoConverter.DtoFromUser(userDtoReq);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

}
