package com.eventosfull.fullapi.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eventosfull.fullapi.model.Eventos;
import com.eventosfull.fullapi.repository.EventoRepository;
import com.eventosfull.fullapi.service.LoggerService;

public class EventoControllerIT {

    @InjectMocks
    private EventoController eventoController; 

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private LoggerService loggerService; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testListaEventos() {
        // Cria dois objetos de Eventos para simular dados no repositório
        Eventos evento1 = new Eventos();
        Eventos evento2 = new Eventos();
        List<Eventos> eventos = Arrays.asList(evento1, evento2);

        // Configura o mock do repositório para retornar a lista de eventos
        when(eventoRepository.findAll()).thenReturn(eventos);

        // Chama o método do controlador
        ResponseEntity<List<Eventos>> response = eventoController.listaEventos();

        // Verifica se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica se a lista de eventos retornada contém dois itens
        assertEquals(2, response.getBody().size());
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testAdicionar() {
        // Cria um objeto Eventos para ser adicionado
        Eventos evento = new Eventos();
        
        // Configura o mock do repositório para retornar o mesmo objeto eventos quando o método save é chamado
        when(eventoRepository.save(evento)).thenReturn(evento);

        // Chama o método do controlador
        Eventos novoEvento = eventoController.adicionar(evento);

        // Verifica se o objeto retornado não é nulo
        assertNotNull(novoEvento);
        // Verifica se o método save do repositório foi chamado uma vez
        verify(eventoRepository, times(1)).save(evento);
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testBuscarEventoPorId() {
        // Cria um objeto Eventos para simular o evento retornado
        Eventos evento = new Eventos();
        Long id = 1L;

        // Configura o mock do repositório para retornar o objeto evento quando o método findById é chamado com o id 1L
        when(eventoRepository.findById(id)).thenReturn(Optional.of(evento));

        // Chama o método do controlador
        ResponseEntity<Eventos> response = eventoController.buscarEventoPorId(id);

        // Verifica se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica se o corpo da resposta é o evento
        assertEquals(evento, response.getBody());
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
        
        // Testa o caso onde o evento não é encontrado
        when(eventoRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<Eventos> responseNotFound = eventoController.buscarEventoPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
        verify(loggerService, times(2)).log(anyString(), anyString()); // Deve ser chamado duas vezes, uma para cada cenário
    }
}
