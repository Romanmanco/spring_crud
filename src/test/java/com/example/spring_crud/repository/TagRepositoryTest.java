package com.example.spring_crud.repository;


import com.example.spring_crud.model.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TagRepositoryTest {

    private final String NAME = "Name";
    private final String NAME_SECOND = "Name 2";
    private final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private final int EXPECTED_VALUE_ONE = 1;
    private final int INDEX_OF_EXPECTED_TAG_ONE = 0;

    @Autowired
    TagRepository repository;

    @Test
    public void findAllTagsTest() {
        init();
        List<Tag> tagList = repository.findAll();
        assertEquals(tagList, tagList);
    }

    @Test
    public void tagSaveTest() {
        init();

        List<Tag> tagList = repository.findAll();

        assertEquals(EXPECTED_VALUE_ONE, tagList.size());
        assertEquals(NAME, tagList.get(INDEX_OF_EXPECTED_TAG_ONE).getName());
        assertEquals(CREATE_TIME, tagList.get(INDEX_OF_EXPECTED_TAG_ONE).getTimeCreate());
    }

    @Test
    public void findByIdTest() {
        init();
        List<Tag> tagList = repository.findAll();
        assertNotNull(tagList);
    }

    @Test
    public void tagUpdateTest() {
        init();
        List<Tag> tagList = repository.findAll();

        Tag storedTag = tagList.get(INDEX_OF_EXPECTED_TAG_ONE);
        storedTag.setName(NAME_SECOND);

        repository.save(storedTag);

        List<Tag> tagListTwo = repository.findAll();
        assertEquals(NAME_SECOND, tagListTwo.get(INDEX_OF_EXPECTED_TAG_ONE).getName());
    }

    private void init() {
        Tag tag = new Tag();
        tag.setName(NAME);
        tag.setTimeCreate(CREATE_TIME);
        repository.save(tag);
    }
}
