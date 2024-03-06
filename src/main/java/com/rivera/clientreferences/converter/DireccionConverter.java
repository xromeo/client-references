package com.rivera.clientreferences.converter;

import com.rivera.clientreferences.dto.DireccionDto;
import com.rivera.clientreferences.dto.PersonaDto;
import com.rivera.clientreferences.model.Direccion;
import com.rivera.clientreferences.model.Persona;
import com.rivera.clientreferences.model.PersonaBuilder;
import org.springframework.stereotype.Component;

@Component
public class DireccionConverter {
    public Direccion dtoToEntity(DireccionDto direccionDto) {
        return new Direccion(
                direccionDto.ubicacionGeografica(),
                direccionDto.zona(),
                direccionDto.calle(),
                direccionDto.numeroDomicilio(),
                direccionDto.referencia()
        );
    }

    public DireccionDto entityToDto(Direccion direccion) {
        return new DireccionDto(
                direccion.ubicacionGeografica(),
                direccion.zona(),
                direccion.calle(),
                direccion.numeroDomicilio(),
                direccion.referencia()
        );
    }
}
