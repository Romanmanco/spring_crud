package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.ItemMapper;
import com.example.spring_crud.model.dto.ItemDto;
import com.example.spring_crud.model.entity.Item;
import com.example.spring_crud.repository.ItemRepository;
import com.example.spring_crud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository repository;
    @Autowired
    ItemMapper mapper;

    @Override
    public List<ItemDto> findAllWithPage(int page, int size) {
        Page<Item> items = repository.findAll(PageRequest.of(page, size));
        List<ItemDto> dtoList = items.stream()
                .map(item -> mapper.entityToDto(item))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public ItemDto getItemById(final Long id) {
        Item item = repository.getById(id);
        return mapper.entityToDto(item);
    }

    public boolean postByIdIsPresent(Long postId){
        return repository.existsById(postId);
    }
}
