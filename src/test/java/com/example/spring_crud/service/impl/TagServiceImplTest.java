package com.example.spring_crud.service.impl;

import com.example.spring_crud.mapper.TagMapper;
import com.example.spring_crud.model.dto.TagDto;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.repository.TagRepository;
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
public class TagServiceImplTest {

    private static final Long STORED_ID = 1L;
    private static final String NAME = "Name";
    private static final LocalDateTime TIME_CREATE = LocalDateTime.now();
    private static final TagDto TAG_DTO = new TagDto(STORED_ID, NAME, TIME_CREATE);
    private static final Tag STORED_TAG = new Tag(STORED_ID, NAME, TIME_CREATE);

    private TagDto tagDto;

    @Spy
    @InjectMocks
    private TagServiceImpl tagService;

    @Mock
    private TagRepository repository;
    @Mock
    private TagMapper mapper;


    @Test
    public void findAllTagsTest() {
        List<Tag> tagList = getTagList();
        List<TagDto> tagDtoList = getTagDtoList();
        PageImpl<Tag> page = new PageImpl<>(tagList);

        Mockito.when(repository.findAll(PageRequest.of(1, 20)))
                .thenReturn(page);
        Mockito.when(mapper.tagToDto(tagList.get(0)))
                .thenReturn(tagDtoList.get(0));

        List<TagDto> dtoList = tagService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals("Name", dtoList.get(0).getName());
    }

    @Test
    public void getTagByIdTest() {
        Mockito.when(repository.getById(STORED_ID))
                .thenReturn(STORED_TAG);
        Mockito.when(mapper.tagToDto(STORED_TAG))
                .thenReturn(TAG_DTO);

        TagDto tagById = tagService.getTagById(STORED_ID);

        assertEquals(TAG_DTO, tagById);
    }

    @Test
    public void tagSaveTest() {
        tagDto = new TagDto(STORED_ID, NAME, TIME_CREATE);
        boolean success = tagService.saveTag(tagDto);
        assertTrue(success);
    }

    @Test
    public void tagDeleteTest() {
        boolean successDel = tagService.deleteById(STORED_ID);

        assertTrue(successDel);
    }

    private List<Tag> getTagList() {
        Tag tag = new Tag();
        tag.setName(NAME);
        tag.setTimeCreate(TIME_CREATE);
        return Arrays.asList(tag);
    }

    private List<TagDto> getTagDtoList() {
        TagDto tagDto = new TagDto();
        tagDto.setName(NAME);
        tagDto.setTimeCreate(TIME_CREATE);
        return Arrays.asList(tagDto);
    }
}
