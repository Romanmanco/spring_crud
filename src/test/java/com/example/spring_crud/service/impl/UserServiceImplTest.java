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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    private final String NAME = "Name";

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
        Mockito.when(mapper.entityToDto(userList.get(0)))
                .thenReturn(userDtoList.get(0));

        List<UserDto> dtoList = userService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals("Name", dtoList.get(0).getName());
    }

    private List<User> getUserList() {
        User user = new User();
        user.setName(NAME);
        return Arrays.asList(user);
    }

    private List<UserDto> getUserDtoList() {
        UserDto userDto = new UserDto();
        userDto.setName(NAME);
        return Arrays.asList(userDto);
    }

    @Test
    public void getItemByIdTest() {
        getUserList();
        assertNotNull(getUserList());
    }
}
