package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.ItemMapper;
import com.example.spring_crud.model.dto.ItemDto;
import com.example.spring_crud.model.entity.Item;
import com.example.spring_crud.repository.ItemRepository;
import com.example.spring_crud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository repository;
    @Autowired
    ItemMapper mapper;

    @Override
    public ItemDto getItemById(final Long id) {
        Item item = repository.getById(id);
        return mapper.entityToDto(item);
    }
}
