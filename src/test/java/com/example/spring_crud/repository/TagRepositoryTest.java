package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TagRepositoryTest {

    private static final String NAME = "Name";
    private static final String NAME_SECOND = "Name 2";
    private static final String NAME_THIRD = "Name 3";
    private static final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private static final LocalDateTime CREATE_TIME_TWO = LocalDateTime.now().plusHours(1);
    private static final LocalDateTime CREATE_TIME_THIRD = LocalDateTime.now().plusDays(1);
    private static final Long STORED_ID = 1L;
    private static final int EXPECTED_VALUE_THREE = 3;
    private static final int INDEX_OF_EXPECTED_TAG_ONE = 0;
    private static final int INDEX_OF_EXPECTED_TAG_TWO = 1;

    @Autowired
    TagRepository repository;

    @Test
    public void tagSaveTest() {
        init();

        List<Tag> tagList = repository.findAll();

        assertEquals(EXPECTED_VALUE_THREE, tagList.size());
        assertEquals(NAME, tagList.get(INDEX_OF_EXPECTED_TAG_ONE).getName());
        assertEquals(CREATE_TIME, tagList.get(INDEX_OF_EXPECTED_TAG_ONE).getTimeCreate());
    }

    @Test
    public void findByIdTest() {
        init();

        Tag storedTag = repository.getById(STORED_ID);
        assertNotNull(storedTag);

        assertEquals(NAME, storedTag.getName());
        assertEquals(CREATE_TIME, storedTag.getTimeCreate());
    }

    //как проверить уникальность ?
    @Test
    public void tagUpdateTest() {
        init();
        List<Tag> tagList = repository.findAll();

        Tag storedTag = tagList.get(INDEX_OF_EXPECTED_TAG_ONE);
        storedTag.setName(NAME_SECOND);
        storedTag.setTimeCreate(CREATE_TIME_TWO);

        repository.save(storedTag);

        List<Tag> tagListTwo = repository.findAll();
//        assertEquals(EXPECTED_VALUE_THREE, tagListTwo.size());
        assertEquals(NAME_SECOND, tagListTwo.get(INDEX_OF_EXPECTED_TAG_ONE).getName());
        assertEquals(CREATE_TIME_TWO, tagListTwo.get(INDEX_OF_EXPECTED_TAG_ONE).getTimeCreate());
    }

    private void init() {
        Tag tag = new Tag();
        tag.setName(NAME);
        tag.setTimeCreate(CREATE_TIME);

        Tag tag2 = new Tag();
        tag2.setName(NAME_SECOND);
        tag2.setTimeCreate(CREATE_TIME_TWO);

        Tag tag3 = new Tag();
        tag3.setName(NAME_THIRD);
        tag3.setTimeCreate(CREATE_TIME_THIRD);

        repository.saveAll(Arrays.asList(tag, tag2, tag3));
    }
}
