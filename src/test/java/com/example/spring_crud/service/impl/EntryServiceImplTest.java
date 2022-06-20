package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.EntryMapper;
import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.EntryDto;
import com.example.spring_crud.model.dto.TagDto;
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
    private static final String BODY_SECOND = "body 2";

    private static final String HEADING = "heading";
    private static final String HEADING_SECOND = "heading 2";

    private static final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private static final LocalDateTime UPDATE_TIME = LocalDateTime.now().plusHours(1);
    private static final LocalDateTime UPDATE_TIME_SECOND = LocalDateTime.now().plusHours(1);

    private static final Long USER_ID = 1L;
    private static final int INDEX_OF_EXPECTED_ENTRY_ONE = 0;
    private static final User USER = new User(USER_ID, "login", "password", "Nick", LocalDateTime.now());

    private static final Tag TAG_ONE = new Tag(STORED_ID_ONE, "Name", LocalDateTime.now());
    private static final Tag TAG_TWO = new Tag(STORED_ID_TWO, "Name2", LocalDateTime.now());
    private static final TagDto TAG_DTO_ONE = new TagDto(STORED_ID_ONE, "Name", LocalDateTime.now());
    private static final TagDto TAG_DTO_TWO = new TagDto(STORED_ID_TWO, "Name2", LocalDateTime.now().plusHours(1));

    private static final List<Tag> TAG_LIST = Arrays.asList(TAG_ONE);
    private static final List<TagDto> TAG_DTO_LIST = Arrays.asList(TAG_DTO_TWO);

    private static final Entry STORED_ENTRY = new Entry(STORED_ID_ONE, BODY, HEADING, CREATE_TIME, UPDATE_TIME, USER, TAG_LIST);
    private static final EntryDto ENTRY_DTO = new EntryDto(STORED_ID_ONE, BODY, HEADING, CREATE_TIME, UPDATE_TIME, USER_ID, TAG_DTO_LIST);

    private EntryDto entryDto;

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
        List<EntryDto> itmDtoList = getEntryDtoList();
        PageImpl<Entry> page = new PageImpl<>(itemsList);

        Mockito.when(repository.findAll(PageRequest.of(1, 20)))
                .thenReturn(page);
        Mockito.when(mapper.entityToDto(itemsList.get(0)))
                .thenReturn(itmDtoList.get(0));

        List<EntryDto> dtoList = entryService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals(BODY, dtoList.get(0).getBody());
    }

    @Test
    public void getEntryByIdTest() {
        Mockito.when(repository.getById(STORED_ID_ONE))
                .thenReturn(STORED_ENTRY);
        Mockito.when(mapper.entityToDto(STORED_ENTRY))
                .thenReturn(ENTRY_DTO);

        EntryDto entryById = entryService.getEntryById(STORED_ID_ONE);

        assertEquals(ENTRY_DTO, entryById);
    }

    //зачем моки ?
    @Test
    public void entrySaveTest() {
//        entryDto = new EntryDto(STORED_ID_ONE, BODY, HEADING, CREATE_TIME, UPDATE_TIME, USER_ID, TAG_DTO_LIST);
//        Mockito.when(repository.getById(STORED_ID_ONE))
//                .thenReturn(STORED_ENTRY);
//        Mockito.when(mapper.entityToDto(STORED_ENTRY))
//                .thenReturn(ENTRY_DTO);

        boolean success = entryService.saveEntry(entryDto);

        assertTrue(success);
    }

    //зачем моки ?
    @Test
    public void entryDeleteTest() {
//        Mockito.when(repository.getById(STORED_ID_ONE))
//                .thenReturn(STORED_ENTRY);
//        Mockito.when(mapper.entityToDto(STORED_ENTRY))
//                .thenReturn(ENTRY_DTO);

        boolean successDel = entryService.deleteById(STORED_ID_ONE);

        assertTrue(successDel);
    }

    @Test
    public void entryUpdateTest() {
        Mockito.when(repository.getById(ENTRY_DTO.getId()))
                .thenReturn(STORED_ENTRY);
        Mockito.when(tagMapper.dtoToEntity(TAG_DTO_TWO))
                .thenReturn(TAG_TWO);

        boolean success = entryService.updateEntry(ENTRY_DTO);

        assertTrue(success);
    }

    private void init() {
        Entry entry = new Entry();
        entry.setHeading(HEADING);
        entry.setBody(BODY);
        entry.setTimeCreate(CREATE_TIME);
        entry.setTimeUpdate(UPDATE_TIME);
        entry.setTagList(TAG_LIST);
        repository.save(entry);
    }

    private List<Entry> getEntryList() {
        Entry entry = new Entry();
        entry.setHeading(HEADING);
        entry.setBody(BODY);
        entry.setTimeCreate(CREATE_TIME);
        entry.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(entry);
    }

    private List<EntryDto> getEntryDtoList(){
        EntryDto entryDto = new EntryDto();
        entryDto.setHeading(HEADING);
        entryDto.setBody(BODY);
        entryDto.setTimeCreate(CREATE_TIME);
        entryDto.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(entryDto);
    }
}