package com.eventosfull.fullapi.repository;

import com.eventosfull.fullapi.model.Inscricao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    @Query("SELECT i FROM Inscricao i WHERE i.id_usuario = :id_usuario")
    List<Inscricao> findByIdUsuario(Long id_usuario);
}
