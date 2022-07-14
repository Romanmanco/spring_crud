package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.UserRequestDto;
import com.example.spring_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Класс для выполнения задач связанных с отображением данных, генерации запросов.
 * Аннотация @RestController позволяет превратить объект в http запрос.
 *
 * @return возвращает dto или сущьность.
 *
 * @author Roman Manko
 * @version 1.0
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Аннотация @GetMapping позволяет сделать get запрос.
     * Аннотация @PathVariable обозначает точку, которая позвояет идентифицировать объект.
     */

    @GetMapping("/user/{id}")
    public UserRequestDto getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    /**
     * Аннотация @PastMapping позволяет сделать post запрос, отправить данные.
     * Аннотация @RequestBody отмечает тело запроса.
     */

    @PostMapping("/user/{id}/edit")
    public boolean userEdit(@RequestBody UserRequestDto dto) {
        return userService.updateUser(dto);
    }

    @PostMapping("/addUser")
    public boolean userAdd(@RequestBody UserRequestDto dto) {
        return userService.saveUser(dto);
    }

    /**
     * Аннотация @DeleteMapping позволяет сделать запрос на удаление данных.
     * Аннотация @PathVariable обозначает точку, которая позвояет идентифицировать объект.
     */

    @DeleteMapping("/user/{id}/remove")
    public boolean deleteUser(@PathVariable(value = "id") Long id) {
        return userService.deleteById(id);
    }
}
