package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.TagDto;

import java.util.List;

public interface TagService {

    List<TagDto> findAllWithPage(int page, int size);

    TagDto getTagById(final Long id);
}
