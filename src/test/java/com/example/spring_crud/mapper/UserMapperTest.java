package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.UserRequestDto;
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

    private static final UserRequestDto USER_DTO = new UserRequestDto(ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);
    private static final User USER = new User(ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);

    @Test
    void userToDto() {
        UserRequestDto dto = USER_DTO;
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
        UserRequestDto userRequestDto = new UserMapper().userToDto(user);

        assertEquals(ID, userRequestDto.getId());
        assertEquals(LOGIN, userRequestDto.getLogin());
        assertEquals(PASSWORD, userRequestDto.getPassword());
        assertEquals(NICK_NAME, userRequestDto.getNickName());
        assertEquals(TIME_REGISTRATION, userRequestDto.getTimeRegistration());
    }
}
