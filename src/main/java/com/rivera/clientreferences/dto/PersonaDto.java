package com.rivera.clientreferences.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PersonaDto(Long id,
                         @NotBlank(message = "Nombre es obligatorio")
                         String nombre,
                         @NotBlank(message = "Apellido Paterno es obligatorio")
                         String apellidoPaterno,
                         String apellidoMaterno,

                         @Past(message = "Fecha Nacimiento es obligatorio")
                         @NotNull(message = "Fecha Nacimiento es obligatorio")
                         LocalDate fechaNacimiento,

                         @NotNull(message = "Direccion es obligatorio")
                         @Valid
                         DireccionDto direccion,

                         @NotBlank(message = "CI es obligatorio")
                         String ci) {
}
