package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.UserDto;

public interface UserService {

    UserDto getUserById(final Long id);

}
