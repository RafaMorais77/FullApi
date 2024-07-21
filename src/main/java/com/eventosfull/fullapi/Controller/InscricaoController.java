package com.eventosfull.fullapi.Controller;


import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.eventosfull.fullapi.model.Inscricao;
import com.eventosfull.fullapi.repository.InscricaoRepository;
import com.eventosfull.fullapi.service.EmailService;
import com.eventosfull.fullapi.service.LoggerService; // Importando o serviço de log

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoRepository inscricaoRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private LoggerService loggerService; // Injetando o serviço de log

    @GetMapping
    public ResponseEntity<List<Inscricao>> listaUsuarios() {
        List<Inscricao> inscricoes = inscricaoRepository.findAll();
        loggerService.log("Listagem de Inscrições", "Listagem de todas as inscrições");
        return ResponseEntity.ok(inscricoes);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inscricao adicionar(@RequestBody Inscricao inscricao) {
        emailService.enviarEmail("rafaeldemorais2019@gmail.com", "Nova Presença Registrada",
                "Uma nova presença foi registrada.");
        
        Inscricao novaInscricao = inscricaoRepository.save(inscricao);
        loggerService.log("Adição de Inscrição", "Nova inscrição registrada: " + novaInscricao.toString());
        return novaInscricao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscarInscricaoPorId(@PathVariable Long id) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(id);
        if (inscricaoOptional.isPresent()) {
            loggerService.log("Busca de Inscrição por ID", "ID da inscrição buscada: " + id);
            return ResponseEntity.ok(inscricaoOptional.get());
        } else {
            loggerService.log("Busca de Inscrição por ID", "Inscrição com ID " + id + " não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarInscricaoPorId(@PathVariable Long id) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(id);
        if (inscricaoOptional.isPresent()) {
            inscricaoRepository.deleteById(id);
            loggerService.log("Remoção de Inscrição por ID", "Inscrição com ID " + id + " removida");
            return ResponseEntity.noContent().build();
        } else {
            loggerService.log("Remoção de Inscrição por ID", "Inscrição com ID " + id + " não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/inscricoes/usuario/{id_usuario}")
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
}
