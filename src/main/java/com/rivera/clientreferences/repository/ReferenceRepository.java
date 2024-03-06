package com.rivera.clientreferences.repository;

import com.rivera.clientreferences.model.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReferenceRepository extends JpaRepository<Referencia, Long> {


    @Query("SELECT r FROM Referencia r JOIN r.persona p WHERE p.id = :personaId")
    Optional<Referencia> findByPersonaId(@Param("personaId") Long personaId);


}
