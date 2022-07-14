package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.UserRequestDto;
import com.example.spring_crud.service.UserService;
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
@WebMvcTest(value = UserController.class)
@Import({UserController.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getUserById() throws Exception{
        UserRequestDto dto = new UserRequestDto();
        dto.setId(1L);
        dto.setLogin("login");
        dto.setPassword("pass");
        dto.setNickName("nick");
        dto.setTimeRegistration(LocalDateTime.now());

        Mockito.when(userService.getUserById(dto.getId()))
                .thenReturn(dto);
        String expectedResponse = objectMapper.writeValueAsString(dto);

        this.mockMvc
                .perform(get("/user/{id}", dto.getId()))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void userEdit() throws Exception{
        UserRequestDto dto = new UserRequestDto();
        dto.setId(1L);
        dto.setLogin("login");
        dto.setPassword("pass");
        dto.setNickName("nick");
        dto.setTimeRegistration(LocalDateTime.now());

        String expectedResponse = objectMapper.writeValueAsString(dto);

        Mockito.when(userService.updateUser(dto))
                .thenReturn(true);

        this.mockMvc
                .perform(post("/user/{id}/edit", dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResponse)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void userAdd() throws Exception{
        UserRequestDto dto = new UserRequestDto();
        dto.setId(1L);
        dto.setLogin("login");
        dto.setPassword("pass");
        dto.setNickName("nick");
        dto.setTimeRegistration(LocalDateTime.now());

        String expectedResponse = objectMapper.writeValueAsString(dto);

        Mockito.when(userService.saveUser(dto))
                .thenReturn(true);

        this.mockMvc
                .perform(post("/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResponse)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void deleteUser() throws Exception {
        Long id = 1L;

        Mockito.when(userService.deleteById(id))
                .thenReturn(true);

        this.mockMvc
                .perform(delete("/user/{id}/remove", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}