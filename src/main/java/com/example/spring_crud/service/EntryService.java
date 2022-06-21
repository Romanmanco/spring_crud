package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.EntryDto;

import java.util.List;

public interface EntryService {

    List<EntryDto> findAllWithPage(int page, int size);

    EntryDto getEntryById(Long id);

    boolean updateEntry(EntryDto dto);

    boolean saveEntry(EntryDto dto);

    boolean deleteById(Long id);
}
