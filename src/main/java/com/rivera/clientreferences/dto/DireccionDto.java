package com.rivera.clientreferences.dto;

import jakarta.validation.constraints.NotBlank;

public record DireccionDto(
        @NotBlank(message = "Ubicación geografica es obligatorio")
        String ubicacionGeografica,
        @NotBlank(message = "Zona es obligatorio")
        String zona,
        @NotBlank(message = "Calle es obligatorio")
        String calle,
        @NotBlank(message = "Numero Domicilio es obligatorio")
        String numeroDomicilio,
        String referencia) {

}
