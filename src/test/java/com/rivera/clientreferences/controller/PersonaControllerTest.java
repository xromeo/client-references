package com.rivera.clientreferences.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rivera.clientreferences.dto.DireccionDto;
import com.rivera.clientreferences.dto.PersonaDto;
import com.rivera.clientreferences.service.PersonaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAddPersonaDireccionMandatory() throws Exception {
        PersonaDto personaDto = new PersonaDto(
                null,
                "Juan",
                "García",
                "López",
                LocalDate.of(1990, 5, 15),
                null,
                "1234567"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/personas/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAddPersona() throws Exception {
        PersonaDto personaDto = new PersonaDto(
                null,
                "Juan",
                "García",
                "López",
                LocalDate.of(1990, 5, 15),
                new DireccionDto("aqui", "zona", "calle","25A", "Parque"),
                "1234567"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/personas/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());;
    }
}