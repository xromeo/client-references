package com.rivera.clientreferences.model;

import com.rivera.clientreferences.service.ReferenceObserver;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "REFERENCIA")
public class Referencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "referencias")
    private List<Cliente> clientes = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
    private String motivo;

    public Referencia() {
    }

    public Referencia(Long id, List<Cliente> clientes, Persona persona, String motivo) {
        this.id = id;
        this.clientes = clientes;
        this.persona = persona;
        this.motivo = motivo;
    }

    public Referencia(Persona persona, String motivo) {
        this.persona = persona;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
