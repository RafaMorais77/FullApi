package com.eventosfull.fullapi.repository;

import com.eventosfull.fullapi.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByLogin(String login);
}
