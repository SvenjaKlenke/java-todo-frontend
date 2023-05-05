package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestKanbanController {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllToDos_thenReturnEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void addToDo_whenAddOrderWithProductIds_then200OK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                                  
                                  "description":"Hallo",
                                  "status":"OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                                  
                                  "description":"Hallo",
                                  "status":"OPEN"
                        }
                        """)).andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void getAllToDosBoard_thenReturnEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void getToDo_thenReturn200OK_returnCorrectToDo() throws Exception {
        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                                  
                                  "description":"Hallo",
                                  "status":"OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/"+toDo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                                  
                                  "description":"Hallo",
                                  "status":"OPEN"
                        }
                        """)).andExpect(jsonPath("$.id").value(toDo.getId()));
    }

    @Test
    @DirtiesContext
    void editToDo_thenReturn200OK_returnCorrectChanceToDo() throws Exception {
        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                                  
                                  "description":"Hallo",
                                  "status":"OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/"+toDo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                                  
                                  "description":"Ciao",
                                  "status":"OPEN",
                                  "id": "1"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                                  
                                  "description":"Ciao",
                                  "status":"OPEN",
                                  "id": "1"
                        }]
                        """));


    }

    @Test
    @DirtiesContext
    void deleteToDo_thenReturn200OK_returnEmptyList() throws Exception {
        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                                  
                                  "description":"Hallo",
                                  "status":"OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/"+toDo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}