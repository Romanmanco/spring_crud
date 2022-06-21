package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tag/{id}")
    public TagDto getTagById(@PathVariable("id") Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/addTag")
    public boolean addTag(@RequestParam TagDto dto) {
        return tagService.saveTag(dto);
    }

    //TODO прочесть статьи
    @PostMapping("/tag/{id}/remove")
    public boolean deleteTag(@PathVariable(value = "id") Long id) {
        return tagService.deleteById(id);
    }
}
