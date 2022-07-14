package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Класс для выполнения задач связанных с отображением данных, генерации запросов.
 * Аннотация @RestController позволяет превратить объект в http запрос.
 *
 * @return возвращает dto или сущьность.
 *
 * @author Roman Manko
 * @version 1.0
 */

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * Аннотация @GetMapping позволяет сделать get запрос.
     * Аннотация @PathVariable обозначает точку, которая позвояет идентифицировать объект.
     */

    @GetMapping("/tag/{id}")
    public TagRequestDto getTagById(@PathVariable(value = "id") Long id) {
        return tagService.getTagById(id);
    }

    /**
     * Аннотация @PastMapping позволяет сделать post запрос, отправить данные.
     * Аннотация @RequestBody отмечает тело запроса.
     */

    @PostMapping("/addTag")
    public boolean addTag(@RequestBody TagRequestDto dto) {
        return tagService.saveTag(dto);
    }

    /**
     * Аннотация @DeleteMapping позволяет сделать запрос на удаление данных.
     * Аннотация @PathVariable обозначает точку, которая позвояет идентифицировать объект.
     */

    @DeleteMapping("/tag/{id}/remove")
    public boolean deleteTag(@PathVariable(value = "id") Long id) {
        return tagService.deleteById(id);
    }
}
