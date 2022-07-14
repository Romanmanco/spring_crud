package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;
import com.example.spring_crud.service.EntryService;
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
public class EntryController {

    @Autowired
    EntryService entryService;

    //todo
//    @GetMapping("/getPageEntry")
//    public List<EntryRequestDto> getPageEntry (@RequestParam Integer page,
//                                               @RequestParam Integer resultCount) {
//        return entryService.findAllWithPage(page, resultCount);
//    }

    /**
     * Аннотация @GetMapping позволяет сделать get запрос.
     * Аннотация @PathVariable обозначает точку, которая позвояет идентифицировать объект.
     */

    @GetMapping("/entry/{id}")
    public EntryResponseDto getEntry (@PathVariable(value = "id") Long id) {
        return entryService.getEntryById(id);
    }

    /**
     * Аннотация @PastMapping позволяет сделать post запрос, отправить данные.
     * Аннотация @RequestBody отмечает тело запроса.
     */

    @PostMapping("/entry/{id}/edit")
    public boolean updateEntry (@RequestBody EntryRequestDto dto) {
        return entryService.updateEntry(dto);
    }

    @PostMapping("/saveEntry")
    public boolean entryAdd (@RequestBody EntryRequestDto dto) {
        return entryService.saveEntry(dto);
    }

    /**
     * Аннотация @DeleteMapping позволяет сделать запрос на удаление данных.
     * Аннотация @PathVariable обозначает точку, которая позвояет идентифицировать объект.
     */

    @DeleteMapping("/entry/{id}/remove")
    public boolean deleteEntry (@PathVariable(value = "id") Long id) {
        return entryService.deleteById(id);
    }
}
