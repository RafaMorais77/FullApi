package com.eventosfull.fullapi.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.eventosfull.fullapi.model.Inscricao;
import com.eventosfull.fullapi.repository.InscricaoRepository;
import com.eventosfull.fullapi.service.LoggerService; // Importando o serviço de log

@RestController
@RequestMapping("/inscricao/usuario")
public class InscricaoPorUsuarioController {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private LoggerService loggerService; // Injetando o serviço de log

    @GetMapping("/{id_usuario}")
    public ResponseEntity<List<Inscricao>> buscarInscricoesPorUsuarioId(@PathVariable Long id_usuario) {
        List<Inscricao> inscricoes = inscricaoRepository.findByIdUsuario(id_usuario);
        if (inscricoes.isEmpty()) {
            loggerService.log("Busca de Inscrições por ID de Usuário", "Nenhuma inscrição encontrada para o usuário com ID " + id_usuario);
            return ResponseEntity.noContent().build();
        } else {
            loggerService.log("Busca de Inscrições por ID de Usuário", "Inscrições encontradas para o usuário com ID " + id_usuario);
            return ResponseEntity.ok(inscricoes);
        }
    }

    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<?> deletarInscricaoPorIdUsuario(@PathVariable Long id_usuario) {
        List<Inscricao> inscricoes = inscricaoRepository.findByIdUsuario(id_usuario);
        if (inscricoes.isEmpty()) {
            loggerService.log("Remoção de Inscrições por ID de Usuário", "Nenhuma inscrição encontrada para remoção do usuário com ID " + id_usuario);
            return ResponseEntity.notFound().build();
        } else {
            inscricaoRepository.deleteAll(inscricoes);
            loggerService.log("Remoção de Inscrições por ID de Usuário", "Inscrições removidas para o usuário com ID " + id_usuario);
            return ResponseEntity.noContent().build();
        }
    }
}
