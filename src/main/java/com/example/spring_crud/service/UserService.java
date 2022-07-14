package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.UserRequestDto;

import java.util.List;

/**
 * Интерфейс содержит методы User, которые нужны для манипуляций с данными.
 *
 * @author Roman Manko
 * @version 1.0
 */

public interface UserService {

    List<UserRequestDto> findAllWithPage(int page, int size);

    UserRequestDto getUserById(Long id);

    boolean updateUser(UserRequestDto dto);

    boolean saveUser(UserRequestDto userRequestDto);

    boolean deleteById(Long id);
}
