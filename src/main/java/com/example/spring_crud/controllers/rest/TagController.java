package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tag/{id}")
    public TagRequestDto getTagById(@PathVariable(value = "id") Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/addTag")
    public boolean addTag(@RequestBody TagRequestDto dto) {
        return tagService.saveTag(dto);
    }

    @PostMapping("/tag/{id}/remove")
    public boolean deleteTag(@PathVariable(value = "id") Long id) {
        return tagService.deleteById(id);
    }
}
