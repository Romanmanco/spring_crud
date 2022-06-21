package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAllWithPage(int page, int size);

    UserDto getUserById(Long id);

    boolean updateUser(Long id);

    boolean saveUser(UserDto userDto);

    boolean deleteById(Long id);
}
