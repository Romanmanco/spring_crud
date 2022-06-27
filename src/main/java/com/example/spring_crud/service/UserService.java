package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.UserRequestDto;

import java.util.List;

public interface UserService {

    List<UserRequestDto> findAllWithPage(int page, int size);

    UserRequestDto getUserById(Long id);

    boolean updateUser(UserRequestDto dto);

    boolean saveUser(UserRequestDto userRequestDto);

    boolean deleteById(Long id);
}
