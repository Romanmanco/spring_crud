package com.example.spring_crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class EntryRequestDto {
    private Long id;
    private String heading;
    private String body;
    private Long userId;
    private List<Long> tagIdList;
}
