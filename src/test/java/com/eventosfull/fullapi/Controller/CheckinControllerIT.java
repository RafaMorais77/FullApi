package com.eventosfull.fullapi.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eventosfull.fullapi.model.Checkin;
import com.eventosfull.fullapi.repository.CheckinRepository;
import com.eventosfull.fullapi.service.LoggerService;

public class CheckinControllerIT {

    @InjectMocks
    private CheckinController checkinController; // Instância real do controlador, com injeção dos mocks

    @Mock
    private CheckinRepository checkinRepository; // Mock do repositório de check-ins

    @Mock
    private LoggerService loggerService; // Mock do serviço de logger

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks antes de cada teste
    }

    @Test
    public void testListaCheckin() {
        // Cria dois objetos de Checkin para simular dados de check-ins no repositório
        Checkin checkin1 = new Checkin();
        Checkin checkin2 = new Checkin();
        List<Checkin> checkins = Arrays.asList(checkin1, checkin2);

        // Configura o mock do repositório para retornar a lista de check-ins
        when(checkinRepository.findAll()).thenReturn(checkins);

        // Chama o método do controlador
        ResponseEntity<List<Checkin>> response = checkinController.listaCheckin();

        // Verifica se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica se o corpo da resposta contém dois check-ins
        assertEquals(2, response.getBody().size());
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testAdicionar() {
        // Cria um objeto Checkin para ser adicionado
        Checkin checkin = new Checkin();
        
        // Configura o mock do repositório para retornar o mesmo objeto checkin quando o método save é chamado
        when(checkinRepository.save(checkin)).thenReturn(checkin);

        // Chama o método do controlador
        Checkin novoCheckin = checkinController.adicionar(checkin);

        // Verifica se o objeto retornado não é nulo
        assertNotNull(novoCheckin);
        // Verifica se o método save do repositório foi chamado uma vez
        verify(checkinRepository, times(1)).save(checkin);
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
    }
}