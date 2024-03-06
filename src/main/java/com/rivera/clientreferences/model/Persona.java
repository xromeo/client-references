package com.rivera.clientreferences.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "PERSONA")
public class Persona {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;

    @Embedded
    private Direccion direccion;

    @Column(unique = true)
    private String ci;

    public Persona() {
    }

    public Persona(Long id,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            LocalDate fechaNacimiento,
            Direccion direccion,
            String ci) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.ci = ci;
    }

    public Long id() {
        return id;
    }

    public String nombre() {
        return nombre;
    }

    public String apellidoPaterno() {
        return apellidoPaterno;
    }

    public String apellidoMaterno() {
        return apellidoMaterno;
    }

    public LocalDate fechaNacimiento() {
        return fechaNacimiento;
    }

    public Direccion direccion() {
        return direccion;
    }


    public String ci() {
        return ci;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Persona) obj;
        return Objects.equals(this.id, that.id) &&
               Objects.equals(this.nombre, that.nombre) &&
               Objects.equals(this.apellidoPaterno, that.apellidoPaterno) &&
               Objects.equals(this.apellidoMaterno, that.apellidoMaterno) &&
               Objects.equals(this.fechaNacimiento, that.fechaNacimiento) &&
               Objects.equals(this.direccion, that.direccion) &&
               Objects.equals(this.ci, that.ci);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, direccion, ci);
    }

    @Override
    public String toString() {
        return "Persona[" +
               "id=" + id + ", " +
               "nombre=" + nombre + ", " +
               "apellidoPaterno=" + apellidoPaterno + ", " +
               "apellidoMaterno=" + apellidoMaterno + ", " +
               "fechaNacimiento=" + fechaNacimiento + ", " +
               "direccion=" + direccion + ", " +
               "ci=" + ci + ']';
    }

}
