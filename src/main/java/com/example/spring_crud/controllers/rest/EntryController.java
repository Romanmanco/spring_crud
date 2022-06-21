package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.EntryDto;
import com.example.spring_crud.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntryController {
    @Autowired
    EntryService entryService;

    @GetMapping("/getPageEntry")
    public String getPageEntry (@RequestParam Integer page,
                                       @RequestParam(required = false) Integer resultCount) {
        entryService.findAllWithPage(page, resultCount);
        return "entryPage";
    }

    @GetMapping("/entry/{id}")
    public EntryDto getEntry (@RequestParam Long id) {
        return entryService.getEntryById(id);
    }

    @PostMapping("/updateEntry")
    public boolean updateEntry (@RequestBody EntryDto dto) {
        return entryService.updateEntry(dto);
    }

    @PostMapping("/saveEntry")
    public boolean entryAdd (EntryDto dto) {
        return entryService.saveEntry(dto);
    }

    @PostMapping("/entry/{id}/remove")
    public boolean deleteEntry (@PathVariable(value = "id") Long id) {
        return entryService.deleteById(id);
    }
}
