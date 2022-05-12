package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.EntryDto;
import com.example.spring_crud.model.entity.Entry;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper {

    public EntryDto entityToDto(final Entry entry) {
        final EntryDto dto = new EntryDto();
        dto.setId(entry.getId());
        dto.setHeading(entry.getHeading());
        dto.setBody(entry.getBody());
        dto.setTimeCreate(entry.getTimeCreate());
        dto.setTimeUpdate(entry.getTimeUpdate());
        dto.setUserId(entry.getUser().getId());
        return dto;
    }
}
