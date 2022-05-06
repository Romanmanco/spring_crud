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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class TagServiceImplTest {

    private final String NAME = "Name";

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
        Mockito.when(mapper.entityToDto(tagList.get(0)))
                .thenReturn(tagDtoList.get(0));

        List<TagDto> dtoList = tagService.findAllWithPage(1, 20);
        assertEquals(1, dtoList.size());
        assertEquals("Name", dtoList.get(0).getName());
    }

    private List<Tag> getTagList() {
        Tag tag = new Tag();
        tag.setName(NAME);
        return Arrays.asList(tag);
    }

    private List<TagDto> getTagDtoList() {
        TagDto tagDto = new TagDto();
        tagDto.setName(NAME);
        return Arrays.asList(tagDto);
    }

    @Test
    public void getItemByIdTest() {
        getTagList();
        assertNotNull(getTagList());
    }
}
