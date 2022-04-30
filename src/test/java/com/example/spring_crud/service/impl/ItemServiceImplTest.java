package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.ItemMapper;
import com.example.spring_crud.model.dto.ItemDto;
import com.example.spring_crud.model.entity.Item;
import com.example.spring_crud.repository.ItemRepository;
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

@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {

    private final String BODY = "body";
    private final String HEADING = "heading";
    private final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private final LocalDateTime UPDATE_TIME = LocalDateTime.now();

    @Spy
    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository repository;
    @Mock
    private ItemMapper mapper;

    @Test
    void findAllWithPage() {
        List<Item> itemsList = getItemList();
        List<ItemDto> itmDtoList = getItemDtoList();
        PageImpl<Item> page = new PageImpl<Item>(itemsList);

        Mockito.when(repository.findAll(PageRequest.of(1, 20)))
                .thenReturn(page);
        Mockito.when(mapper.entityToDto(itemsList.get(0)))
                .thenReturn(itmDtoList.get(0));

        List<ItemDto> dtoList = itemService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals("body", dtoList.get(0).getBody());
    }

    private List<Item> getItemList() {
        Item item = new Item();
        item.setTimeUpdate(LocalDateTime.now());
        item.setHeading(HEADING);
        item.setBody(BODY);
        item.setTimeCreate(CREATE_TIME);
        item.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(item);
    }

    private List<ItemDto> getItemDtoList(){
        ItemDto item = new ItemDto();
        item.setHeading(HEADING);
        item.setBody(BODY);
        item.setTimeCreate(CREATE_TIME);
        item.setTimeUpdate(UPDATE_TIME);
        return Arrays.asList(item);
    }


    @Test
    void getItemById() {
    }
}