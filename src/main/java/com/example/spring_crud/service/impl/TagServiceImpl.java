package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.repository.TagRepository;
import com.example.spring_crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository repository;


    @Autowired
    TagMapper mapper;

    //todo Добавить методы createEntity,
//deleteEntity в каждом сервисе и сделать их реализацию.

    @Override
    public List<TagDto> findAllWithPage(int page, int size) {
        Page<Tag> tags = repository.findAll(PageRequest.of(page, size));
        List<TagDto> dtoList = tags.stream()
                .map(tag -> mapper.entityToDto(tag))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public TagDto getTagById(final Long id) {
        Tag tag = repository.getById(id);
        return mapper.entityToDto(tag);
    }

    @Override
    public boolean saveTag(TagDto tagDto) {
        Tag tag = mapper.dtoToEntity(tagDto);
        try {
            repository.save(tag);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(final Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
