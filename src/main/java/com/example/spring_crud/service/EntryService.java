package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.EntryDto;

import java.util.List;

public interface EntryService {

    List<EntryDto> findAllWithPage(int page, int size);

    EntryDto getItemById(final Long id);

    boolean postByIdIsPresent(Long postId);
}
