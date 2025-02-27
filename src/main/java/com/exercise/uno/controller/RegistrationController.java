package com.exercise.uno.controller;

import com.exercise.uno.mapper.UserMapper;
import com.exercise.uno.models.dto.DTOConverter;
import com.exercise.uno.models.dto.UserDTO;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.UserService;
import org.hibernate.ObjectNotFoundException;
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
    UserService userService;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDto) {
        if(userRepository.existsByUsername(userDto.getUsername())){
            return ResponseEntity.badRequest().body("Username already exist");
        }
        User user = UserMapper.INSTANCE.toEntity(userDto);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping
    @RequestMapping("/2")
    public ResponseEntity<String> registerUser2(@RequestBody UserDTO userDto) {
        try{
            userService.save(userDto);
            return ResponseEntity.ok("User registered successfully");
        }catch (ObjectNotFoundException e){
            return ResponseEntity.badRequest().body("Username already exist");
        }
    }

}
