package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAllWithPage(int page, int size);

    ItemDto getItemById(final Long id);
}
