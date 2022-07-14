package com.example.spring_crud.service;

import com.example.spring_crud.model.dto.TagRequestDto;

import java.util.List;

/**
 * Интерфейс содержит методы Tag, которые нужны для манипуляций с данными.
 *
 * @author Roman Manko
 * @version 1.0
 */

public interface TagService {

    List<TagRequestDto> findAllWithPage(int page, int size);

    TagRequestDto getTagById(Long id);

    boolean saveTag(TagRequestDto tagRequestDto);

    boolean deleteById(Long id);
}
