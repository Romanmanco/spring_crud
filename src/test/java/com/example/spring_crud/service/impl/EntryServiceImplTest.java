package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.EntryMapper;
import com.example.spring_crud.model.dto.EntryDto;
import com.example.spring_crud.model.entity.Entry;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class EntryServiceImplTest {

    private final String BODY = "body";
    private final String HEADING = "heading";
    private final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private final LocalDateTime UPDATE_TIME = LocalDateTime.now();

    @Spy
    @InjectMocks
    private EntryServiceImpl itemService;

    @Mock
    private EntryRepository repository;
    @Mock
    private EntryMapper mapper;

    @Test
    public void findAllWithPageTest() {
        List<Entry> itemsList = getItemList();
        List<EntryDto> itmDtoList = getItemDtoList();
        PageImpl<Entry> page = new PageImpl<>(itemsList);

        Mockito.when(repository.findAll(PageRequest.of(1, 20)))
                .thenReturn(page);
        Mockito.when(mapper.entityToDto(itemsList.get(0)))
                .thenReturn(itmDtoList.get(0));

        List<EntryDto> dtoList = itemService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals("body", dtoList.get(0).getBody());
    }

    private List<Entry> getItemList() {
        Entry entry = new Entry();
        entry.setTimeUpdate(LocalDateTime.now());
        entry.setHeading(HEADING);
        entry.setBody(BODY);
        entry.setTimeCreate(CREATE_TIME);
        entry.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(entry);
    }

    private List<EntryDto> getItemDtoList(){
        EntryDto item = new EntryDto();
        item.setHeading(HEADING);
        item.setBody(BODY);
        item.setTimeCreate(CREATE_TIME);
        item.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(item);
    }


    @Test
    public void getItemByIdTest() {
        getItemList();
        assertNotNull(getItemList());
    }
}