package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.EntryMapper;
import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;
import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.repository.EntryRepository;
import com.example.spring_crud.repository.TagRepository;
import com.example.spring_crud.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс реализует методы интерфейса EntryService.
 * Для записи/удаления/нахождения/обновления используется методы из класса EntryRepository, TagRepository.
 * Для преобразования данных используются методы из класса EntryMapper, TagMapper.
 * Аннотация @Service помечает что компонент содерожит бизнес-логику приложения.
 * Аннотация @Autowired используется для проводки связи между объектами.
 *
 * @author Roman Manko
 * @version 1.0
 */

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    EntryRepository repository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    EntryMapper mapper;

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<EntryResponseDto> findAllWithPage(int page, int size) {
        Page<Entry> items = repository.findAll(PageRequest.of(page, size));
        List<EntryResponseDto> dtoList = items.stream()
                .map(item -> mapper.entityToResponseDto(item))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public EntryResponseDto getEntryById(Long id) {
        Entry entry = repository.getById(id);
        return mapper.entityToResponseDto(entry);
    }

    @Override
    public boolean updateEntry(EntryRequestDto dto) {
        Entry entry = repository.getById(dto.getId());
        entry.setId(dto.getId());
        entry.setHeading(dto.getHeading());
        entry.setBody(dto.getBody());
        entry.setUser(entry.getUser());
        entry.setTagList(entry.getTagList());
        try {
            repository.save(entry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean saveEntry(EntryRequestDto entryRequestDto) {
        LocalDateTime dateTime = LocalDateTime.now();

        Entry entry = mapper.requestDtoToEntity(entryRequestDto);
        entry.setTimeCreate(dateTime);
        entry.setTimeUpdate(dateTime);
        try {
            repository.save(entry);
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