package com.example.spring_crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDto {

    private Long id;
    private String login;
    private String password;
    private String nickName;
    private LocalDateTime timeRegistration;
}
