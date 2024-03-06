package com.rivera.clientreferences.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rivera.clientreferences.dto.ClienteDto;
import com.rivera.clientreferences.dto.DireccionDto;
import com.rivera.clientreferences.dto.PersonaDto;
import com.rivera.clientreferences.service.ClienteService;
import com.rivera.clientreferences.service.ReferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAddClientePersonaNoexiste() throws Exception {
        ClienteDto clienteDto = new ClienteDto(
                null,
                1L,
                "cliente@example.com",
                "1234567890",
                "Estudiante"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAddCliente() throws Exception {

        PersonaDto personaDto = new PersonaDto(
                null,
                "Juan",
                "García",
                "López",
                LocalDate.of(1990, 5, 15),
                new DireccionDto("aqui", "zona", "calle","25A", "Parque"),
                "1734567"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/personas/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());;


        ClienteDto clienteDto = new ClienteDto(
                null,
                1L,
                "cliente@example.com",
                "1234567890",
                "Estudiante"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}