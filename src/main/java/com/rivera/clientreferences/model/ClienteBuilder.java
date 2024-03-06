package com.rivera.clientreferences.model;

import com.rivera.clientreferences.type.AccesibilidadCliente;
import com.rivera.clientreferences.type.EstadoCliente;

import java.util.List;

public class ClienteBuilder {
    private Long id;
    private Persona persona;
    private String email;
    private String phoneNumber;
    private String ocupacion;
    private int edad;
    private EstadoCliente estado;

    private AccesibilidadCliente accesibilidad;
    private List<Referencia> referencias;

    public ClienteBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ClienteBuilder setPersona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public ClienteBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClienteBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClienteBuilder setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
        return this;
    }

    public ClienteBuilder setEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public ClienteBuilder setEstado(EstadoCliente estado) {
        this.estado = estado;
        return this;
    }

    public ClienteBuilder setAccesibilidad(AccesibilidadCliente accesibilidad) {
        this.accesibilidad = accesibilidad;
        return this;
    }

    public ClienteBuilder setReferencias(List<Referencia> referencias) {
        this.referencias = referencias;
        return this;
    }

    public Cliente build() {
        return new Cliente(id,
                persona,
                email,
                phoneNumber,
                ocupacion,
                edad,
                estado,
                accesibilidad,
                referencias);
    }
}