package com.eventosfull.fullapi.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.eventosfull.fullapi.model.Eventos;
import com.eventosfull.fullapi.repository.EventoRepository;
import com.eventosfull.fullapi.service.LoggerService; // Importando o serviço de log

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private LoggerService loggerService; // Injetando o serviço de log

    @GetMapping
    public ResponseEntity<List<Eventos>> listaEventos() {
        List<Eventos> eventos = eventoRepository.findAll();
        loggerService.log("Listagem de Eventos", "Listagem de todos os eventos");
        return ResponseEntity.ok(eventos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Eventos adicionar(@RequestBody Eventos evento) {
        Eventos novoEvento = eventoRepository.save(evento);
        loggerService.log("Adição de Evento", "Novo evento adicionado: " + novoEvento.toString());
        return novoEvento;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eventos> buscarEventoPorId(@PathVariable Long id) {
        Optional<Eventos> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            loggerService.log("Busca de Evento por ID", "ID do evento buscado: " + id);
            return ResponseEntity.ok(eventoOptional.get());
        } else {
            loggerService.log("Busca de Evento por ID", "Evento com ID " + id + " não encontrado");
            return ResponseEntity.notFound().build();
        }
    }
}
