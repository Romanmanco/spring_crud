package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tag/{id}")
    public TagDto getTagById(final @PathVariable("id") Long id) {
        return tagService.getTagById(id);
    }
}
