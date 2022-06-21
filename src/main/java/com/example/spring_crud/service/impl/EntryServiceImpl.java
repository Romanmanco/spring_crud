package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.EntryMapper;
import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.EntryDto;
import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.repository.EntryRepository;
import com.example.spring_crud.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    EntryRepository repository;
    @Autowired
    EntryMapper mapper;
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<EntryDto> findAllWithPage(int page, int size) {
        Page<Entry> items = repository.findAll(PageRequest.of(page, size));
        List<EntryDto> dtoList = items.stream()
                .map(item -> mapper.entityToDto(item))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public EntryDto getEntryById(Long id) {
        Entry entry = repository.getById(id);
        return mapper.entityToDto(entry);
    }

    @Override
    public boolean updateEntry(EntryDto dto) {
        //найти запись
        Entry entry = repository.getById(dto.getId());

        List<Tag> tagList = getTagList(dto.getTagList());
        //заполнить значения
        entry.setHeading(dto.getHeading());
        entry.setBody(dto.getBody());
        entry.setTimeUpdate(LocalDateTime.now());
        entry.setTagList(tagList);

        //сохранить
        try {
            repository.save(entry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //TODO все приватные методы должны быть под публичными
    private List<Tag> getTagList(List<TagDto> tagList) {
        List<Tag> listTag = new ArrayList<>();
        for (TagDto element : tagList) {
            Tag tag = tagMapper.dtoToTag(element);
            listTag.add(tag);
        }
        return listTag;
    }

    @Override
    public boolean saveEntry(EntryDto entryDto) {
        Entry entry = mapper.dtoToEntity(entryDto);
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
//получить записи постранично по тегу(на будущее, если все готово