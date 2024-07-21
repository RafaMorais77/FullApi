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
import com.eventosfull.fullapi.model.Checkin;
import com.eventosfull.fullapi.repository.CheckinRepository;
import com.eventosfull.fullapi.service.LoggerService; 

@RestController
@RequestMapping("/presencas")
public class CheckinController {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private LoggerService loggerService; 

    @GetMapping
    public ResponseEntity<List<Checkin>> listaCheckin() {
        List<Checkin> checkins = checkinRepository.findAll();
        loggerService.log("Listagem de Check-ins", "Listagem de todos os check-ins");
        return ResponseEntity.ok(checkins);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Checkin adicionar(@RequestBody Checkin checkin) {
        Checkin novoCheckin = checkinRepository.save(checkin);
        loggerService.log("Adição de Check-in", "Novo check-in adicionado: " + novoCheckin.toString());
        return novoCheckin;
    }
}