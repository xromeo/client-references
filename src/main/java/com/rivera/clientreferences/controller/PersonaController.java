package com.rivera.clientreferences.controller;

import com.rivera.clientreferences.dto.PersonaDto;
import com.rivera.clientreferences.service.PersonaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);
    public static final String LOG_MESSAGE_ADD_PERSON = "Requesting add Person wit payload Person = {0}";


    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/")
    public ResponseEntity<PersonaDto> addPersona(@Valid @RequestBody PersonaDto personaDto) {
        logger.info(MessageFormat.format(LOG_MESSAGE_ADD_PERSON, personaDto));
        PersonaDto personaDtoSaved = personaService.addPersona(personaDto);
        return new ResponseEntity<>(personaDtoSaved, HttpStatus.CREATED);
    }
}
