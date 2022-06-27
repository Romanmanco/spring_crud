package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.UserMapper;
import com.example.spring_crud.model.dto.UserRequestDto;
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

    @Override
    public List<UserRequestDto> findAllWithPage(int page, int size) {
        Page<User> users = repository.findAll(PageRequest.of(page, size));
        List<UserRequestDto> dtoList = users.stream()
                .map(user -> mapper.userToDto(user))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserRequestDto getUserById(Long id) {
        User user = repository.getById(id);
        return mapper.userToDto(user);
    }

    @Override
    public boolean updateUser(UserRequestDto dto) {
        User user = repository.getById(dto.getId());
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setNickName(dto.getNickName());
        user.setTimeRegistration(dto.getTimeRegistration());
        try {
            repository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean saveUser(UserRequestDto userRequestDto) {
        User user = mapper.dtoToUser(userRequestDto);
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
