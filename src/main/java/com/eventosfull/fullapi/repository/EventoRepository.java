package com.eventosfull.fullapi.repository;

import com.eventosfull.fullapi.model.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Eventos, Long> {
}