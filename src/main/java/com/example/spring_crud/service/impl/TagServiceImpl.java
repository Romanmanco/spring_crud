package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.repository.TagRepository;
import com.example.spring_crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс реализует методы интерфейса TagService.
 * Для записи/удаления/нахождения/обновления используется методы из класса TagRepository.
 * Для преобразования данных используются методы из класса TagMapper.
 * Аннотация @Service помечает что компонент содерожит бизнес-логику приложения.
 * Аннотация @Autowired используется для проводки связи между объектами.
 *
 * @author Roman Manko
 * @version 1.0
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository repository;

    @Autowired
    TagMapper mapper;

    @Override
    public List<TagRequestDto> findAllWithPage(int page, int size) {
        Page<Tag> tags = repository.findAll(PageRequest.of(page, size));
        List<TagRequestDto> dtoList = tags.stream()
                .map(tag -> mapper.tagToDto(tag))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public TagRequestDto getTagById(Long id) {
        Tag tag = repository.getById(id);
        return mapper.tagToDto(tag);
    }

    @Override
    public boolean saveTag(TagRequestDto tagRequestDto) {
        Tag tag = mapper.dtoToTag(tagRequestDto);
        try {
            repository.save(tag);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
