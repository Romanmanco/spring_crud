package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.ItemDto;
import com.example.spring_crud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/item/{id}")
    public ItemDto getItemById(final @PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }
}
