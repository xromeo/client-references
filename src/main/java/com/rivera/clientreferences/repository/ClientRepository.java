package com.rivera.clientreferences.repository;

import com.rivera.clientreferences.model.Cliente;
import com.rivera.clientreferences.type.AccesibilidadCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByAccesibilidad(AccesibilidadCliente accesibilidad);

    @Query("SELECT c FROM Cliente c WHERE c.persona.id IN :personaIds")
    List<Cliente> findByPersonaIds(@Param("personaIds") List<Long> personaIds);
}
