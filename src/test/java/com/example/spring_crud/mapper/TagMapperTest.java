package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.model.entity.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TagMapperTest {

    private static final Long ID = 1L;
    private static final String TAG_NAME = "Name";
    private static final LocalDateTime CREATE_TIME = LocalDateTime.now();

    private static final Tag TAG = new Tag(ID, TAG_NAME, CREATE_TIME);
    private static final TagDto TAG_DTO = new TagDto(ID, TAG_NAME, CREATE_TIME);

    @Test
    void tagToDto() {
        TagDto dto = TAG_DTO;
        Tag tag = new TagMapper().dtoToTag(dto);

        assertEquals(ID, tag.getId());
        assertEquals(TAG_NAME, tag.getName());
        assertEquals(CREATE_TIME, tag.getTimeCreate());
    }

    @Test
    void dtoToTag() {
        Tag tag = TAG;
        TagDto tagDto = new TagMapper().tagToDto(tag);

        assertEquals(ID, tagDto.getId());
        assertEquals(TAG_NAME, tagDto.getName());
        assertEquals(CREATE_TIME, tagDto.getTimeCreate());
    }
}
