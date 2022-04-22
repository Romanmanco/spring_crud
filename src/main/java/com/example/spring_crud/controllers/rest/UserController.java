package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.UserDto;
import com.example.spring_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public UserDto getUserById(final @PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
