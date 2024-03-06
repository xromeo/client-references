package com.rivera.clientreferences.repository;

import com.rivera.clientreferences.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Persona, Long> {
}
