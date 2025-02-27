package com.exercise.uno.service;

import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    public String login(String username, String password)throws EntityNotFoundException{
        User user = userRepository.findByUsername(username);
        if( user == null || !user.getPassword().equals(password)){
            throw new EntityNotFoundException("Wrong username or password");
        }
        return jwtUtil.generateToken(username);
    }



}
