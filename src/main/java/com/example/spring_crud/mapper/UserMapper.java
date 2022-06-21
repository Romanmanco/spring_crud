package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto userToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setNickName(user.getNickName());
        dto.setTimeRegistration(user.getTimeRegistration());
        return dto;
    }

    public User dtoToUser(UserDto userDto) {
        User entity = new User();
        entity.setId(userDto.getId());
        entity.setLogin(userDto.getLogin());
        entity.setPassword(userDto.getPassword());
        entity.setNickName(userDto.getNickName());
        entity.setTimeRegistration(userDto.getTimeRegistration());
        return entity;
    }
}
