package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.TagRequestDto;
import com.example.spring_crud.service.TagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TagController.class)
@Import({TagController.class})
@AutoConfigureMockMvc(addFilters = false)
class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TagService tagService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getTagById() throws Exception {
        TagRequestDto dto = new TagRequestDto();
        dto.setId(1L);
        dto.setName("name");
        dto.setTimeCreate(LocalDateTime.now());

        Mockito.when(tagService.getTagById(dto.getId()))
                .thenReturn(dto);
        String expectedResponse = objectMapper.writeValueAsString(dto);

        this.mockMvc
                .perform(get("/tag/{id}", dto.getId()))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void addTag() throws Exception {
        TagRequestDto dto = new TagRequestDto();
        dto.setId(1L);
        dto.setName(dto.getName());
        dto.setTimeCreate(dto.getTimeCreate());

        String expectedResponse = objectMapper.writeValueAsString(dto);

        Mockito.when(tagService.saveTag(dto))
                .thenReturn(true);

        this.mockMvc.perform(post("/addTag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResponse)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void deleteTag() throws Exception {
        Long id = 1L;

        Mockito.when(tagService.deleteById(id))
                .thenReturn(true);

        this.mockMvc.perform(delete("/tag/{id}/remove", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}