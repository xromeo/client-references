package com.rivera.clientreferences.service;

import com.rivera.clientreferences.converter.PersonaConverter;
import com.rivera.clientreferences.dto.PersonaDto;
import com.rivera.clientreferences.model.Persona;
import com.rivera.clientreferences.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    private static final Logger logger = LoggerFactory.getLogger(PersonaService.class);

    private final PersonaConverter personaConverter;
    private final PersonRepository personRepository;


    public PersonaService(PersonaConverter personaConverter, PersonRepository personRepository) {
        this.personaConverter = personaConverter;
        this.personRepository = personRepository;
    }

    public PersonaDto addPersona(PersonaDto personaDto) {
        Persona persona = personaConverter.dtoToEntity(personaDto);

        Persona saved = personRepository.save(persona);
        logger.info("Saved Person = " + saved);
        PersonaDto personaDtoSaved = personaConverter.entityToDto(saved);
        return personaDtoSaved;
    }
}
