package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.TagDto;

public interface TagService {

    TagDto getTagById(final Long id);
}
