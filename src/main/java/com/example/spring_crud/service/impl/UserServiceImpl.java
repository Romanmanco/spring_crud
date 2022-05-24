package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.UserMapper;
import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.model.entity.User;
import com.example.spring_crud.repository.UserRepository;
import com.example.spring_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    UserMapper mapper;

//todo Добавить методы createEntity, updateEntity,
//deleteEntity в каждом сервисе и сделать их реализацию.

    @Override
    public List<UserDto> findAllWithPage(int page, int size) {
        Page<User> users = repository.findAll(PageRequest.of(page, size));
        List<UserDto> dtoList = users.stream()
                .map(user -> mapper.entityToDto(user))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDto getUserById(final Long id) {
        User user = repository.getById(id);
        return mapper.entityToDto(user);
    }
}
