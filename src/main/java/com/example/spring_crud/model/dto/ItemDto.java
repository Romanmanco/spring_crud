package com.example.spring_crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto {

    private Long id;
    private String heading;
    private String body;
    private LocalDateTime timeCreate;
    private LocalDateTime timeUpdate;
    private Long userId;
}
