package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.model.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagDto tagToDto(Tag tag) {
        TagDto dto = new TagDto();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setTimeCreate(tag.getTimeCreate());
        return dto;
    }

    public Tag dtoToTag(TagDto tagDto) {
        Tag entity = new Tag();
        entity.setId(tagDto.getId());
        entity.setName(tagDto.getName());
        entity.setTimeCreate(tagDto.getTimeCreate());
        return entity;
    }
}
