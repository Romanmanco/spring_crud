package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto entityToDto(final User user) {
        final UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }
}
