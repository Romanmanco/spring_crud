package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.EntryDto;

import java.util.List;

public interface EntryService {

    List<EntryDto> findAllWithPage(final int page, final int size);

    EntryDto getEntryById(final Long id);

    boolean updateEntry(final EntryDto dto);

    boolean saveEntry(final EntryDto entryDto);

    boolean deleteById(final Long id);
}
