package com.example.spring_crud.mapper;

import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;
import com.example.spring_crud.model.dto.TagResponseDto;
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
    private static final TagResponseDto TAG_DTO_ONE = new TagResponseDto(STORED_ID_ONE, "Name", LocalDateTime.now());
    private static final List<Long> TAG_DTO_LIST = Arrays.asList(TAG_DTO_ONE.getId());
    private static final EntryRequestDto ENTRY_DTO = new EntryRequestDto(
            STORED_ID_ONE,
            HEADING,
            BODY,
            USER_ID,
            TAG_DTO_LIST
    );

    @Test
    void entityToDto() {
        Entry entry = ENTRY;
        EntryResponseDto entryRequestDto = new EntryMapper().entityToResponseDto(entry);

        assertEquals(BODY, entryRequestDto.getBody());
        assertEquals(HEADING, entryRequestDto.getHeading());
        assertEquals(USER_ID, entryRequestDto.getId());

    }

    @Test
    void dtoToEntity() {
        EntryRequestDto dto = ENTRY_DTO;
        Entry entry = new EntryMapper().requestDtoToEntity(dto);

        assertEquals(BODY, entry.getBody());
        assertEquals(HEADING, entry.getHeading());
        assertEquals(USER_ID, entry.getId());
    }
}