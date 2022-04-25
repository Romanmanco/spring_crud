package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.ItemDto;

public interface ItemService {

    ItemDto getItemById(final Long id);

}
