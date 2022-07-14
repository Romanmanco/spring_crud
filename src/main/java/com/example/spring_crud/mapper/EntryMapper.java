package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;
import com.example.spring_crud.model.entity.Entry;
import org.springframework.stereotype.Component;

/**
 * Класс используется для преобразования сущьности в dto или dto в сущьность.
 * Аннотация @Component позволяет Spring определять компонент программы.
 *
 * @return возвращает dto или сущьность.
 *
 * @author Roman Manko
 * @version 1.0
 */

@Component
public class EntryMapper {

    public EntryResponseDto entityToResponseDto(Entry entry) {
        EntryResponseDto dto = new EntryResponseDto();
        dto.setId(entry.getId());
        dto.setHeading(entry.getHeading());
        dto.setBody(entry.getBody());
        dto.setTimeCreate(entry.getTimeCreate());
        dto.setTimeUpdate(entry.getTimeUpdate());
        dto.setUserId(entry.getUser().getId());
        return dto;
    }

    public Entry requestDtoToEntity(EntryRequestDto entryRequestDto) {
        Entry entity = new Entry();
        entity.setId(entryRequestDto.getId());
        entity.setHeading(entryRequestDto.getHeading());
        entity.setBody(entryRequestDto.getBody());
        entity.setUser(entity.getUser());
        return entity;
    }
}
