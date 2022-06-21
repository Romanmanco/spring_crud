package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.repository.UserRepository;
import com.example.spring_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{id}/edit")
    public boolean userEdit(@PathVariable(value = "id") Long id) {
        return userService.updateUser(id);
    }

    @PostMapping("/addUser")
    public boolean userAdd (UserDto dto) {
        return userService.saveUser(dto);
    }

    @PostMapping("/user/{id}/remove")
    public boolean deleteUser (@PathVariable(value = "id") Long id) {
        return userService.deleteById(id);
    }
}
