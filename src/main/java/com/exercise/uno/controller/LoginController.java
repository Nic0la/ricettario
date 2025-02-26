package com.exercise.uno.controller;

import com.exercise.uno.models.entity.LoginRequest;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request)throws EntityNotFoundException{
        User user = userRepository.findByUsername(request.getUsername());
        if(!user.getPassword().equals(request.getPassword())) {
            throw new EntityNotFoundException("Wrong password");
        }
        return ResponseEntity.ok("Login con username: " + request.getUsername() + " " + request.getPassword());

    }
}
