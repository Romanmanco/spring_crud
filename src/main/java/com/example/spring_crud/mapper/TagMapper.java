package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.model.entity.Tag;
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
public class TagMapper {

    public TagRequestDto tagToDto(Tag tag) {
        TagRequestDto dto = new TagRequestDto();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setTimeCreate(tag.getTimeCreate());
        return dto;
    }

    public Tag dtoToTag(TagRequestDto tagRequestDto) {
        Tag entity = new Tag();
        entity.setId(tagRequestDto.getId());
        entity.setName(tagRequestDto.getName());
        entity.setTimeCreate(tagRequestDto.getTimeCreate());
        return entity;
    }
}
