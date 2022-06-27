package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;
import com.example.spring_crud.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/entry/{id}")
    public EntryResponseDto getEntry (@PathVariable(value = "id") Long id) {
        return entryService.getEntryById(id);
    }

    @PostMapping("/updateEntry")
    public boolean updateEntry (@RequestBody EntryRequestDto dto) {
        return entryService.updateEntry(dto);
    }

    @PostMapping("/saveEntry")
    public boolean entryAdd (@RequestBody EntryRequestDto dto) {
        return entryService.saveEntry(dto);
    }

    @PostMapping("/entry/{id}/remove")
    public boolean deleteEntry (@PathVariable(value = "id") Long id) {
        return entryService.deleteById(id);
    }
}
