package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.repository.TagRepository;
import com.example.spring_crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository repository;
    @Autowired
    TagMapper mapper;


    @Override
    public TagDto getTagById(final Long id) {
        Tag tag = repository.getById(id);
        return mapper.entityToDto(tag);
    }
}
