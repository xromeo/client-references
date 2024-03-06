package com.rivera.clientreferences.model;

import java.time.LocalDate;

public class PersonaBuilder {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private Direccion direccion;
    private String ci;

    public PersonaBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonaBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PersonaBuilder setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
        return this;
    }

    public PersonaBuilder setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
        return this;
    }

    public PersonaBuilder setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public PersonaBuilder setDireccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public PersonaBuilder setCi(String ci) {
        this.ci = ci;
        return this;
    }

    public Persona build() {
        return new Persona(id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, direccion, ci);
    }
}