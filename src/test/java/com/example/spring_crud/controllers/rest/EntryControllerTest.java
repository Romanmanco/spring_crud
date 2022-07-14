package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.EntryRequestDto;
import com.example.spring_crud.model.dto.EntryResponseDto;
import com.example.spring_crud.service.EntryService;
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
@WebMvcTest(value = EntryController.class)
@Import({EntryController.class})
@AutoConfigureMockMvc(addFilters = false)
class EntryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EntryService entryService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getEntry() throws Exception {
        EntryResponseDto dto = new EntryResponseDto();
        dto.setId(1L);
        dto.setHeading("blabla head");
        dto.setBody("body");
        dto.setTimeCreate(LocalDateTime.now());
        dto.setTimeUpdate(LocalDateTime.now());
        dto.setUserId(1l);
        dto.setTagList(dto.getTagList());

        Mockito.when(entryService.getEntryById(dto.getId()))
                .thenReturn(dto);
        String expectedResponse = objectMapper.writeValueAsString(dto);

        this.mockMvc
                .perform(get("/entry/{id}", dto.getId()))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void updateEntry() throws Exception {
        EntryRequestDto dto = new EntryRequestDto();
        dto.setId(1L);
        dto.setHeading("blabla head");
        dto.setBody("body");
        dto.setUserId(1l);
        dto.setTagIdList(dto.getTagIdList());

        String expectedResponse = objectMapper.writeValueAsString(dto);

        Mockito.when(entryService.updateEntry(dto))
                .thenReturn(true);

        this.mockMvc
                .perform(post("/entry/{id}/edit", dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResponse)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void entryAdd() throws Exception {
        EntryRequestDto dto = new EntryRequestDto();
        dto.setId(1L);
        dto.setHeading("blabla head");
        dto.setBody("body");
        dto.setUserId(1l);
        dto.setTagIdList(dto.getTagIdList());

        String expectedResponse = objectMapper.writeValueAsString(dto);

        Mockito.when(entryService.saveEntry(dto))
                .thenReturn(true);

        this.mockMvc
                .perform(post("/saveEntry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResponse)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void deleteEntry() throws Exception {
        Long id = 1L;

        Mockito.when(entryService.deleteById(id))
                .thenReturn(true);

        this.mockMvc
                .perform(delete("/entry/{id}/remove", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}