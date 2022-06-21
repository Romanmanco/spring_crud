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

//todo Добавить метод, updateEntity,

    @Override
    public List<UserDto> findAllWithPage(int page, int size) {
        Page<User> users = repository.findAll(PageRequest.of(page, size));
        List<UserDto> dtoList = users.stream()
                .map(user -> mapper.userToDto(user))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = repository.getById(id);
        return mapper.userToDto(user);
    }

    @Override
    public boolean updateUser(Long id) {
        return true;
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        User user = mapper.dtoToUser(userDto);
        try {
            repository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UserServiceImpl() {
        super();
    }
}
