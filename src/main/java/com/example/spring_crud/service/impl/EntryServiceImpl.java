package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.EntryMapper;
import com.example.spring_crud.model.dto.EntryDto;
import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.repository.EntryRepository;
import com.example.spring_crud.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    EntryRepository repository;
    @Autowired
    EntryMapper mapper;

    @Override
    public List<EntryDto> findAllWithPage(int page, int size) {
        Page<Entry> items = repository.findAll(PageRequest.of(page, size));
        List<EntryDto> dtoList = items.stream()
                .map(item -> mapper.entityToDto(item))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public EntryDto getItemById(final Long id) {
        Entry entry = repository.getById(id);
        return mapper.entityToDto(entry);
    }

    public boolean postByIdIsPresent(Long postId){
        return repository.existsById(postId);
    }
}
