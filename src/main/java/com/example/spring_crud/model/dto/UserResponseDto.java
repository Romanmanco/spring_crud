package com.example.spring_crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс описывает сущьность User, её поля, в ответ на запрос.
 * Благодаря аннотациям из библиотеки lombok @AllArgsConstructor и @NoArgsConstructor не нужно
 * прописывать конструкторы.
 * @Data — это удобная сокращённая аннотация, которая содержит в себе возможности из @ToString,
 * @EqualsAndHashCode, @Getter / @Setter и @RequiredArgsConstructor. Другими словами,
 * @Data генерирует весь бойлерплейт код, который обычно связан с обычными POJO (Plain Old Java Objects).
 *
 * @author Roman Manko
 * @version 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {

    private Long id;
    private String login;
    private String password;
    private String nickName;
    private LocalDateTime timeRegistration;
}
