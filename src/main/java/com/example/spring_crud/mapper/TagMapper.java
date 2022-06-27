package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.model.entity.Tag;
import org.springframework.stereotype.Component;

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
