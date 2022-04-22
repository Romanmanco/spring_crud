package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.UserMapper;
import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.model.entity.User;
import com.example.spring_crud.repository.UserRepository;
import com.example.spring_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    UserMapper mapper;

    @Override
    public UserDto getUserById(final Long id) {
        User user = repository.getById(id);
        return mapper.entityToDto(user);
    }
}
