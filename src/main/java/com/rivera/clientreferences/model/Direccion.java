package com.rivera.clientreferences.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Direccion(
        String ubicacionGeografica,
         String zona,
         String calle,
         String numeroDomicilio,
         String referencia) {
    
}
