package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.EntryMapper;
import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;
import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.model.dto.TagResponseDto;
import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.model.entity.User;
import com.example.spring_crud.repository.EntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class EntryServiceImplTest {

    private static final Long STORED_ID_ONE = 1L;
    private static final Long STORED_ID_TWO = 2L;

    private static final String BODY = "body";

    private static final String HEADING = "heading";

    private static final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private static final LocalDateTime UPDATE_TIME = LocalDateTime.now().plusHours(1);

    private static final Long USER_ID = 1L;
    private static final User USER = new User(
            USER_ID,
            "login",
            "password",
            "Nick",
            LocalDateTime.now());

    private static final Tag TAG_ONE = new Tag(STORED_ID_ONE, "Name", LocalDateTime.now());
    private static final List<Tag> TAG_LIST = Arrays.asList(TAG_ONE);
    private static final Entry STORED_ENTRY = new Entry(
            STORED_ID_ONE,
            HEADING,
            BODY,
            CREATE_TIME,
            UPDATE_TIME,
            USER,
            TAG_LIST
    );
    private static final TagRequestDto TAG_REQUEST_DTO_TWO = new TagRequestDto(STORED_ID_TWO, "Name1", CREATE_TIME);
    private static final TagResponseDto TAG__RESPONSE_DTO_TWO = new TagResponseDto(STORED_ID_TWO, "Name1", CREATE_TIME);
    private static final Tag TAG_TWO = new Tag(STORED_ID_TWO, "Name2", LocalDateTime.now());
    private static final List<TagResponseDto> TAG_DTO_LIST = Arrays.asList(TAG__RESPONSE_DTO_TWO);
    private static final List<Long> TAG_IDS_LIST = Arrays.asList(STORED_ID_TWO);
    private static final EntryResponseDto ENTRY_DTO = new EntryResponseDto(
            STORED_ID_ONE,
            HEADING,
            BODY,
            CREATE_TIME,
            UPDATE_TIME,
            USER_ID,
            TAG_DTO_LIST
    );
    private static final EntryRequestDto ENTRY_REQUEST_DTO = new EntryRequestDto(
            STORED_ID_ONE,
            HEADING,
            BODY,
            USER_ID,
            TAG_IDS_LIST
    );

    @Spy
    @InjectMocks
    private EntryServiceImpl entryService;

    @Mock
    private EntryRepository repository;
    @Mock
    private EntryMapper mapper;
    @Mock
    private TagMapper tagMapper;

    @Test
    public void findAllWithPageTest() {
        List<Entry> itemsList = getEntryList();
        List<EntryResponseDto> itmDtoList = getEntryDtoList();
        PageImpl<Entry> page = new PageImpl<>(itemsList);

        Mockito.when(repository.findAll(PageRequest.of(1, 20)))
                .thenReturn(page);
        Mockito.when(mapper.entityToResponseDto(itemsList.get(0)))
                .thenReturn(itmDtoList.get(0));

        List<EntryResponseDto> dtoList = entryService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals(BODY, dtoList.get(0).getBody());
    }

    @Test
    public void getEntryByIdTest() {
        Mockito.when(repository.getById(STORED_ID_ONE))
                .thenReturn(STORED_ENTRY);
        Mockito.when(mapper.entityToResponseDto(STORED_ENTRY))
                .thenReturn(ENTRY_DTO);

        EntryResponseDto entryById = entryService.getEntryById(STORED_ID_ONE);

        assertEquals(ENTRY_DTO, entryById);
    }

    @Test
    public void entrySaveTest() {
        boolean success = entryService.saveEntry(ENTRY_REQUEST_DTO);
        assertTrue(success);
    }

    @Test
    public void entryDeleteTest() {
        boolean successDel = entryService.deleteById(STORED_ID_ONE);
        assertTrue(successDel);
    }

    @Test
    public void entryUpdateTest() {
        Mockito.when(repository.getById(ENTRY_DTO.getId()))
                .thenReturn(STORED_ENTRY);
        Mockito.when(tagMapper.dtoToTag(TAG_REQUEST_DTO_TWO))
                .thenReturn(TAG_TWO);

        boolean success = entryService.updateEntry(ENTRY_REQUEST_DTO);

        assertTrue(success);
    }

    private List<Entry> getEntryList() {
        Entry entry = new Entry();
        entry.setHeading(HEADING);
        entry.setBody(BODY);
        entry.setTimeCreate(CREATE_TIME);
        entry.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(entry);
    }

    private List<EntryResponseDto> getEntryDtoList() {
        EntryResponseDto entryRequestDto = new EntryResponseDto();
        entryRequestDto.setHeading(HEADING);
        entryRequestDto.setBody(BODY);
        entryRequestDto.setTimeCreate(CREATE_TIME);
        entryRequestDto.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(entryRequestDto);
    }
}