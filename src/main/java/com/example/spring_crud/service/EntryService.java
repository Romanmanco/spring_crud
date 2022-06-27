package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;

import java.util.List;

public interface EntryService {

    List<EntryResponseDto> findAllWithPage(int page, int size);

    EntryResponseDto getEntryById(Long id);

    boolean updateEntry(EntryRequestDto dto);

    boolean saveEntry(EntryRequestDto dto);

    boolean deleteById(Long id);
}
