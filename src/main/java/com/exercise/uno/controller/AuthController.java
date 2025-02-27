package com.exercise.uno.controller;

import com.exercise.uno.service.JwtUtil;
import com.exercise.uno.models.entity.LoginRequest;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername());
        if(!user.getPassword().equals(request.getPassword())) {
            throw new EntityNotFoundException("Wrong password");
        }else{
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
    }

    static class AuthResponse {
        public String token;
        public AuthResponse(String token) {
            this.token = token;}
    }

}
