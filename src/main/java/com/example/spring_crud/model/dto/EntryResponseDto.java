package com.example.spring_crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс описывает сущьность Entry, её поля, в ответ на запрос.
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
public class EntryResponseDto {

    private Long id;
    private String heading;
    private String body;
    private LocalDateTime timeCreate;
    private LocalDateTime timeUpdate;
    private Long userId;
    private List<TagResponseDto> tagList;
}
