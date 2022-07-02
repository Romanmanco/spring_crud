package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TagMapperTest {

    private static final Long ID = 1L;
    private static final String TAG_NAME = "Name";
    private static final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private static final Entry ENTRY = new Entry();
    private static final List<Entry> ENTRY_LIST = Arrays.asList(ENTRY);
    private static final Tag TAG = new Tag(ID, TAG_NAME, CREATE_TIME, ENTRY_LIST);
    private static final TagRequestDto TAG_DTO = new TagRequestDto(ID, TAG_NAME, CREATE_TIME);

    @Test
    void tagToDto() {
        TagRequestDto dto = TAG_DTO;
        Tag tag = new TagMapper().dtoToTag(dto);

        assertEquals(ID, tag.getId());
        assertEquals(TAG_NAME, tag.getName());
        assertEquals(CREATE_TIME, tag.getTimeCreate());
    }

    @Test
    void dtoToTag() {
        Tag tag = TAG;
        TagRequestDto tagRequestDto = new TagMapper().tagToDto(tag);

        assertEquals(ID, tagRequestDto.getId());
        assertEquals(TAG_NAME, tagRequestDto.getName());
        assertEquals(CREATE_TIME, tagRequestDto.getTimeCreate());
    }
}
