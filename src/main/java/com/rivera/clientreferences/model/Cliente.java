package com.rivera.clientreferences.model;

import com.rivera.clientreferences.service.ReferenceObserver;
import com.rivera.clientreferences.type.AccesibilidadCliente;
import com.rivera.clientreferences.type.EstadoCliente;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENTE")
public class Cliente{
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private Persona persona;
    private String email;
    private String phoneNumber;
    private String ocupacion;
    private int edad;
    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

    @Enumerated(EnumType.STRING)
    private AccesibilidadCliente accesibilidad;

    @ManyToMany
    @JoinTable(
            name = "cliente_referencia",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "referencia_id"))
    private List<Referencia> referencias;

    public Cliente() {
    }

    public Cliente(Long id,
            Persona persona,
            String email,
            String phoneNumber,
            String ocupacion,
            int edad,
            EstadoCliente estado,
            AccesibilidadCliente accesibilidad,
            List<Referencia> referencias) {
        this.id = id;
        this.persona = persona;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.ocupacion = ocupacion;
        this.edad = edad;
        this.estado = estado;
        this.accesibilidad = accesibilidad;
        this.referencias = referencias;
    }

    public Long id() {
        return id;
    }

    public Persona persona() {
        return persona;
    }

    public String email() {
        return email;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String ocupacion() {
        return ocupacion;
    }

    public int edad() {
        return edad;
    }

    public EstadoCliente estado() {
        return estado;
    }

    public List<Referencia> referencias() {
        return referencias;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }

    public AccesibilidadCliente getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(AccesibilidadCliente accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public List<Referencia> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<Referencia> referencias) {
        this.referencias = referencias;
    }

    @Override
    public String toString() {
        return "Cliente[" +
               "id=" + id + ", " +
               "persona=" + persona + ", " +
               "email=" + email + ", " +
               "phoneNumber=" + phoneNumber + ", " +
               "ocupacion=" + ocupacion + ", " +
               "edad=" + edad + ", " +
               "estado=" + estado + ", " +
               "referencias=" + referencias + ']';
    }
}
