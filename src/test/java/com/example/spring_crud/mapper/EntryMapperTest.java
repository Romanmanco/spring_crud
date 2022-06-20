package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.EntryDto;
import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class EntryMapperTest {

    private static final Long STORED_ID_ONE = 1L;

    private static final String BODY = "body";
    private static final String HEADING = "heading";
    private static final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private static final LocalDateTime UPDATE_TIME = LocalDateTime.now().plusHours(1);
    private static final Long USER_ID = 1L;
    private static final User USER = new User(USER_ID, "login", "password", "Nick", LocalDateTime.now());
    private static final Tag TAG_ONE = new Tag(STORED_ID_ONE, "Name", LocalDateTime.now());
    private static final List<Tag> TAG_LIST = Arrays.asList(TAG_ONE);
    private static final Entry ENTRY = new Entry(STORED_ID_ONE, HEADING, BODY, CREATE_TIME, UPDATE_TIME, USER, TAG_LIST);
    private static final TagDto TAG_DTO_ONE = new TagDto(STORED_ID_ONE, "Name", LocalDateTime.now());
    private static final List<TagDto> TAG_DTO_LIST = Arrays.asList(TAG_DTO_ONE);
    private static final EntryDto ENTRY_DTO = new EntryDto(STORED_ID_ONE, HEADING, BODY, CREATE_TIME, UPDATE_TIME, USER_ID, TAG_DTO_LIST);

    @Test
    void entityToDto() {
        EntryDto dto = ENTRY_DTO;
        Entry entry = new EntryMapper().dtoToEntity(dto);

        assertEquals(BODY, entry.getBody());
        assertEquals(HEADING, entry.getHeading());
        assertEquals(CREATE_TIME, entry.getTimeCreate());
        assertEquals(UPDATE_TIME, entry.getTimeUpdate());
        assertEquals(USER_ID, entry.getId());
    }

    @Test
    void dtoToEntity() {
        Entry entry = ENTRY;
        EntryDto entryDto = new EntryMapper().entityToDto(entry);

        assertEquals(BODY, entryDto.getBody());
        assertEquals(HEADING, entryDto.getHeading());
        assertEquals(CREATE_TIME, entryDto.getTimeCreate());
        assertEquals(UPDATE_TIME, entryDto.getTimeUpdate());
        assertEquals(USER_ID, entryDto.getId());
    }
}