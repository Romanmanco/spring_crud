package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.UserRequestDto;
import com.example.spring_crud.repository.UserRepository;
import com.example.spring_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public UserRequestDto getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user/{id}/edit")
    public boolean userEdit(@RequestBody UserRequestDto dto) {
        return userService.updateUser(dto);
    }

    @PostMapping("/addUser")
    public boolean userAdd (@RequestBody UserRequestDto dto) {
        return userService.saveUser(dto);
    }

    @PostMapping("/user/{id}/remove")
    public boolean deleteUser (@PathVariable(value = "id") Long id) {
        return userService.deleteById(id);
    }
}
