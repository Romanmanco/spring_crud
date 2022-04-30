package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemRepositoryTest {

    private final String HEADING = "heading";
    private final String BODY = "body";
    private final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private final LocalDateTime UPDATE_TIME = LocalDateTime.now();
    private final int EXPECTED_VALUE_ONE = 1;

    @Autowired
    ItemRepository repository;

    @Test
    public void getAllItem() {
        init();

        List<Item> itemList = repository.findAll();

        assertEquals(EXPECTED_VALUE_ONE, itemList.size());
        assertEquals(BODY, itemList.get(0).getBody());
        assertEquals(HEADING, itemList.get(0).getHeading());
        assertEquals(CREATE_TIME, itemList.get(0).getTimeCreate());
        assertEquals(CREATE_TIME, itemList.get(0).getTimeUpdate());

    }

    private void init() {
        Item item = new Item();
        item.setHeading(HEADING);
        item.setBody(BODY);
        item.setTimeCreate(CREATE_TIME);
        item.setTimeUpdate(CREATE_TIME);

        repository.save(item);
    }
}