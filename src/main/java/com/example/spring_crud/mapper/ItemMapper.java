package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.ItemDto;
import com.example.spring_crud.model.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDto entityToDto(final Item item) {
        final ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setHeading(item.getHeading());
        dto.setBody(item.getBody());
        dto.setTimeCreate(item.getTimeCreate());
        dto.setTimeUpdate(item.getTimeUpdate());
//        dto.setUserId(item.getUserId());
        return dto;
    }
}
