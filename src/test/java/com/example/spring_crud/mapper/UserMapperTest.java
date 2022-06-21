package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class UserMapperTest {

    private static final Long ID = 1L;
    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Password";
    private static final String NICK_NAME = "Name";
    private static final LocalDateTime TIME_REGISTRATION = LocalDateTime.now();

    private static final UserDto USER_DTO = new UserDto(ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);
    private static final User USER = new User(ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);

    @Test
    void userToDto() {
        UserDto dto = USER_DTO;
        User user = new UserMapper().dtoToUser(dto);

        assertEquals(ID, user.getId());
        assertEquals(LOGIN, user.getLogin());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(NICK_NAME, user.getNickName());
        assertEquals(TIME_REGISTRATION, user.getTimeRegistration());
    }

    @Test
    void dtoToUser() {
        User user = USER;
        UserDto userDto = new UserMapper().userToDto(user);

        assertEquals(ID, userDto.getId());
        assertEquals(LOGIN, userDto.getLogin());
        assertEquals(PASSWORD, userDto.getPassword());
        assertEquals(NICK_NAME, userDto.getNickName());
        assertEquals(TIME_REGISTRATION, userDto.getTimeRegistration());
    }
}
