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

import com.eventosfull.fullapi.model.Inscricao;
import com.eventosfull.fullapi.repository.InscricaoRepository;
import com.eventosfull.fullapi.service.LoggerService;

public class InscricaoPorUsuarioControllerIT {

    @InjectMocks
    private InscricaoPorUsuarioController inscricaoPorUsuarioController;

    @Mock
    private InscricaoRepository inscricaoRepository;

    @Mock
    private LoggerService loggerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarInscricoesPorUsuarioId() {
        Long idUsuario = 1L;
        Inscricao inscricao1 = new Inscricao();
        Inscricao inscricao2 = new Inscricao();
        List<Inscricao> inscricoes = Arrays.asList(inscricao1, inscricao2);

        when(inscricaoRepository.findByIdUsuario(idUsuario)).thenReturn(inscricoes);

        ResponseEntity<List<Inscricao>> response = inscricaoPorUsuarioController.buscarInscricoesPorUsuarioId(idUsuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(loggerService, times(1)).log(anyString(), anyString());

        when(inscricaoRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList());
        ResponseEntity<List<Inscricao>> responseNoContent = inscricaoPorUsuarioController.buscarInscricoesPorUsuarioId(idUsuario);
        assertEquals(HttpStatus.NO_CONTENT, responseNoContent.getStatusCode());
        verify(loggerService, times(2)).log(anyString(), anyString());
    }

    @Test
    public void testDeletarInscricaoPorIdUsuario() {
        Long idUsuario = 1L;
        Inscricao inscricao1 = new Inscricao();
        Inscricao inscricao2 = new Inscricao();
        List<Inscricao> inscricoes = Arrays.asList(inscricao1, inscricao2);

        when(inscricaoRepository.findByIdUsuario(idUsuario)).thenReturn(inscricoes);

        ResponseEntity<?> response = inscricaoPorUsuarioController.deletarInscricaoPorIdUsuario(idUsuario);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(inscricaoRepository, times(1)).deleteAll(inscricoes);
        verify(loggerService, times(1)).log(anyString(), anyString());

        when(inscricaoRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList());
        ResponseEntity<?> responseNotFound = inscricaoPorUsuarioController.deletarInscricaoPorIdUsuario(idUsuario);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
        verify(loggerService, times(2)).log(anyString(), anyString());
    }
}
