package com.rivera.clientreferences.dto;

import com.rivera.clientreferences.type.EstadoCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDto(Long id,
                         @NotNull(message = "PersonaId es obligatorio")
                         Long personaId,
                         @Email(message = "La dirección de correo electrónico no es válida")
                         String email,
                         @NotBlank(message = "El telefono es obligatorio")
                         String telefono,
                         @NotBlank(message = "Ocupación es obligatorio")
                         String ocupacion
                         ) {
}
