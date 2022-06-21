package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.UserMapper;
import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.model.entity.User;
import com.example.spring_crud.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    private static final Long STORED_ID = 1L;
    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Password";
    private static final String NICK_NAME = "Name";
    private static final LocalDateTime TIME_REGISTRATION = LocalDateTime.now();
    private static final UserDto USER_DTO = new UserDto(STORED_ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);
    private static final User STORED_USER = new User(STORED_ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);

    private UserDto userDto;

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    @Test
    public void findAllUsersTest() {
        List<User> userList = getUserList();
        List<UserDto> userDtoList = getUserDtoList();
        PageImpl<User> page = new PageImpl<>(userList);

        Mockito.when(repository.findAll(PageRequest.of(1, 20)))
                .thenReturn(page);
        Mockito.when(mapper.userToDto(userList.get(0)))
                .thenReturn(userDtoList.get(0));

        List<UserDto> dtoList = userService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals("Name", dtoList.get(0).getNickName());
    }

    @Test
    public void getUserByIdTest() {
        Mockito.when(repository.getById(STORED_ID))
                .thenReturn(STORED_USER);
        Mockito.when(mapper.userToDto(STORED_USER))
                .thenReturn(USER_DTO);

        UserDto userById = userService.getUserById(STORED_ID);

        assertEquals(USER_DTO, userById);
    }

    @Test
    public void userSaveTest() {
        userDto = new UserDto(STORED_ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);
        boolean success = userService.saveUser(userDto);
        assertTrue(success);
    }

    @Test
    public void userDeleteTest() {
        boolean successDel = userService.deleteById(STORED_ID);
        assertTrue(successDel);
    }

    private List<User> getUserList() {
        User user = new User();
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setNickName(NICK_NAME);
        user.setTimeRegistration(TIME_REGISTRATION);
        return Arrays.asList(user);
    }

    private List<UserDto> getUserDtoList() {
        UserDto userDto = new UserDto();
        userDto.setLogin(LOGIN);
        userDto.setPassword(PASSWORD);
        userDto.setNickName(NICK_NAME);
        userDto.setTimeRegistration(TIME_REGISTRATION);
        return Arrays.asList(userDto);
    }
}
