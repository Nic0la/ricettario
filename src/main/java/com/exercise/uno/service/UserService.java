package com.exercise.uno.service;

import com.exercise.uno.mapper.UserMapper;
import com.exercise.uno.models.dto.UserDTO;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    public User save(UserDTO user)throws ObjectNotFoundException{
        Optional<User> userOptional = Optional.ofNullable(UserMapper.INSTANCE.toEntity(user));
        if(userRepository.existsByUsername(userOptional.get().getUsername())){
            throw new ObjectNotFoundException(userOptional, "user is not correct");
        }
        if(userOptional.isPresent()) {
            userOptional.get().setPassword(encoder.encode(userOptional.get().getPassword()));
            return userRepository.save(userOptional.get());
        }else
            throw new ObjectNotFoundException(userOptional, "user is not correct");
    }

}
