package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.UserMapper;
import com.example.spring_crud.model.dto.UserRequestDto;
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
    private static final UserRequestDto USER_DTO = new UserRequestDto(STORED_ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);
    private static final User STORED_USER = new User(STORED_ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);

    private UserRequestDto userRequestDto;

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
        List<UserRequestDto> userRequestDtoList = getUserDtoList();
        PageImpl<User> page = new PageImpl<>(userList);

        Mockito.when(repository.findAll(PageRequest.of(1, 20)))
                .thenReturn(page);
        Mockito.when(mapper.userToDto(userList.get(0)))
                .thenReturn(userRequestDtoList.get(0));

        List<UserRequestDto> dtoList = userService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals("Name", dtoList.get(0).getNickName());
    }

    @Test
    public void getUserByIdTest() {
        Mockito.when(repository.getById(STORED_ID))
                .thenReturn(STORED_USER);
        Mockito.when(mapper.userToDto(STORED_USER))
                .thenReturn(USER_DTO);

        UserRequestDto userById = userService.getUserById(STORED_ID);

        assertEquals(USER_DTO, userById);
    }

    @Test
    public void userSaveTest() {
        userRequestDto = new UserRequestDto(STORED_ID, LOGIN, PASSWORD, NICK_NAME, TIME_REGISTRATION);
        boolean success = userService.saveUser(userRequestDto);
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

    private List<UserRequestDto> getUserDtoList() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setLogin(LOGIN);
        userRequestDto.setPassword(PASSWORD);
        userRequestDto.setNickName(NICK_NAME);
        userRequestDto.setTimeRegistration(TIME_REGISTRATION);
        return Arrays.asList(userRequestDto);
    }
}
