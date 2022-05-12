package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto entityToDto(final User user) {
        final UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setNickName(user.getNickName());
        dto.setTmeRegistration(user.getTimeRegistration());
        return dto;
    }
}
