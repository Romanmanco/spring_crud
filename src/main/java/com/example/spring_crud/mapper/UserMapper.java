package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.UserRequestDto;
import com.example.spring_crud.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * Класс используется для преобразования сущьности в dto или dto в сущьность.
 * Аннотация @Component позволяет Spring определять компонент программы.
 *
 * @return возвращает dto или сущьность.
 *
 * @author Roman Manko
 * @version 1.0
 */

@Component
public class UserMapper {

    public UserRequestDto userToDto(User user) {
        UserRequestDto dto = new UserRequestDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setNickName(user.getNickName());
        dto.setTimeRegistration(user.getTimeRegistration());
        return dto;
    }

    public User dtoToUser(UserRequestDto userRequestDto) {
        User entity = new User();
        entity.setId(userRequestDto.getId());
        entity.setLogin(userRequestDto.getLogin());
        entity.setPassword(userRequestDto.getPassword());
        entity.setNickName(userRequestDto.getNickName());
        entity.setTimeRegistration(userRequestDto.getTimeRegistration());
        return entity;
    }
}
