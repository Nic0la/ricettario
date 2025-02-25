package com.exercise.uno.mapper;

import com.exercise.uno.modules.dto.RecipeDTO;
import com.exercise.uno.modules.dto.UserDTO;
import com.exercise.uno.modules.entity.Recipe;
import com.exercise.uno.modules.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {RecipeMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
