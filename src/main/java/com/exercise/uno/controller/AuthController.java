package com.exercise.uno.controller;

import com.exercise.uno.service.AuthService;
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
    AuthService authService;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        try {
            String token = authService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    static class AuthResponse {
        public String token;

        public AuthResponse(String token) {
            this.token = token;
        }
    }

}
