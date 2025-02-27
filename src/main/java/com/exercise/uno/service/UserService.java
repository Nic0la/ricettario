package com.exercise.uno.service;

import com.exercise.uno.mapper.UserMapper;
import com.exercise.uno.models.dto.UserDTO;
import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.exception.EntityNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    RecipeRepository recipeRepository;

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

    public User addRecipe(String username, String recipeName) throws EntityNotFoundException{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            System.out.println("Failed");
            throw new RuntimeException("Utente non autenticato");
        }

        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new EntityNotFoundException("User not found");
        Recipe recipe = recipeRepository.findByName(recipeName);
        if(recipe==null)
            throw new EntityNotFoundException("Recipe not found");

        recipe.setUser(user);
        user.getRecipes().add(recipe);

        userRepository.save(user);
        recipeRepository.save(recipe);
        return user;
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.toDTOList(users);
    }

}
