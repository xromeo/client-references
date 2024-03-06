package com.rivera.clientreferences.converter;

import com.rivera.clientreferences.dto.DireccionDto;
import com.rivera.clientreferences.dto.PersonaDto;
import com.rivera.clientreferences.model.Direccion;
import com.rivera.clientreferences.model.Persona;
import com.rivera.clientreferences.model.PersonaBuilder;
import org.springframework.stereotype.Component;

@Component
public class PersonaConverter {

    private final DireccionConverter direccionConverter;

    public PersonaConverter(DireccionConverter direccionConverter) {
        this.direccionConverter = direccionConverter;
    }

    public Persona dtoToEntity(PersonaDto personaDto) {

        Direccion direccion = direccionConverter.dtoToEntity(personaDto.direccion());

        return new PersonaBuilder()
                .setApellidoPaterno(personaDto.apellidoPaterno())
                .setApellidoMaterno(personaDto.apellidoMaterno())
                .setNombre(personaDto.nombre())
                .setDireccion(direccion)
                .setCi(personaDto.ci())
                .setFechaNacimiento(personaDto.fechaNacimiento())
                .build();
    }

    public PersonaDto entityToDto(Persona persona) {
        DireccionDto direccionDto = direccionConverter.entityToDto(persona.direccion());

        return new PersonaDto(
                persona.id(),
                persona.nombre(),
                persona.apellidoPaterno(),
                persona.apellidoMaterno(),
                persona.fechaNacimiento(),
                direccionDto,
                persona.ci()
        );
    }
}
