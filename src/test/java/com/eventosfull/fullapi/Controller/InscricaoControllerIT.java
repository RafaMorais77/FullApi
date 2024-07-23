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

import com.eventosfull.fullapi.model.Inscricao;
import com.eventosfull.fullapi.repository.InscricaoRepository;
import com.eventosfull.fullapi.service.EmailService;
import com.eventosfull.fullapi.service.LoggerService;

public class InscricaoControllerIT {

    @InjectMocks
    private InscricaoController inscricaoController; 

    @Mock
    private InscricaoRepository inscricaoRepository; 

    @Mock
    private EmailService emailService; 

    @Mock
    private LoggerService loggerService; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testListaUsuarios() {
        // Cria dois objetos de Inscricao para simular dados no repositório
        Inscricao inscricao1 = new Inscricao();
        Inscricao inscricao2 = new Inscricao();
        List<Inscricao> inscricoes = Arrays.asList(inscricao1, inscricao2);

        // Configura o mock do repositório para retornar a lista de inscrições
        when(inscricaoRepository.findAll()).thenReturn(inscricoes);

        // Chama o método do controlador
        ResponseEntity<List<Inscricao>> response = inscricaoController.listaUsuarios();

        // Verifica se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica se a lista de inscrições retornada contém dois itens
        assertEquals(2, response.getBody().size());
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testAdicionar() {
        // Cria um objeto Inscricao para ser adicionado
        Inscricao inscricao = new Inscricao();
        
        // Configura o mock do repositório para retornar o mesmo objeto Inscricao quando o método save é chamado
        when(inscricaoRepository.save(inscricao)).thenReturn(inscricao);

        // Chama o método do controlador
        Inscricao novaInscricao = inscricaoController.adicionar(inscricao);

        // Verifica se o objeto retornado não é nulo
        assertNotNull(novaInscricao);
        // Verifica se o método save do repositório foi chamado uma vez
        verify(inscricaoRepository, times(1)).save(inscricao);
        // Verifica se o método enviarEmail do EmailService foi chamado uma vez
        verify(emailService, times(1)).enviarEmail(anyString(), anyString(), anyString());
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testBuscarInscricaoPorId() {
        // Cria um objeto Inscricao para simular a inscrição retornada
        Inscricao inscricao = new Inscricao();
        Long id = 1L;

        // Configura o mock do repositório para retornar o objeto Inscricao quando o método findById é chamado com o id 1L
        when(inscricaoRepository.findById(id)).thenReturn(Optional.of(inscricao));

        // Chama o método do controlador
        ResponseEntity<Inscricao> response = inscricaoController.buscarInscricaoPorId(id);

        // Verifica se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica se o corpo da resposta é a inscrição
        assertEquals(inscricao, response.getBody());
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());
        
        // Testa o caso onde a inscrição não é encontrada
        when(inscricaoRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<Inscricao> responseNotFound = inscricaoController.buscarInscricaoPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
        verify(loggerService, times(2)).log(anyString(), anyString()); // Deve ser chamado duas vezes, uma para cada cenário
    }

    @Test
    public void testDeletarInscricaoPorId() {
        // Cria um objeto Inscricao para simular a inscrição existente
        Inscricao inscricao = new Inscricao();
        Long id = 1L;

        // Configura o mock do repositório para retornar o objeto Inscricao quando o método findById é chamado com o id 1L
        when(inscricaoRepository.findById(id)).thenReturn(Optional.of(inscricao));

        // Chama o método do controlador
        ResponseEntity<?> response = inscricaoController.deletarInscricaoPorId(id);

        // Verifica se o status da resposta é NO_CONTENT (204)
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Verifica se o método deleteById do repositório foi chamado uma vez
        verify(inscricaoRepository, times(1)).deleteById(id);
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());

        // Testa o caso onde a inscrição não é encontrada
        when(inscricaoRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<?> responseNotFound = inscricaoController.deletarInscricaoPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
        verify(loggerService, times(2)).log(anyString(), anyString()); // Deve ser chamado duas vezes, uma para cada cenário
    }

    @Test
    public void testBuscarInscricoesPorUsuarioId() {
        // Cria dois objetos de Inscricao para simular dados no repositório
        Inscricao inscricao1 = new Inscricao();
        Inscricao inscricao2 = new Inscricao();
        List<Inscricao> inscricoes = Arrays.asList(inscricao1, inscricao2);
        Long idUsuario = 1L;

        // Configura o mock do repositório para retornar a lista de inscrições quando o método findByIdUsuario é chamado
        when(inscricaoRepository.findByIdUsuario(idUsuario)).thenReturn(inscricoes);

        // Chama o método do controlador
        ResponseEntity<List<Inscricao>> response = inscricaoController.buscarInscricoesPorUsuarioId(idUsuario);

        // Verifica se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica se a lista de inscrições retornada contém dois itens
        assertEquals(2, response.getBody().size());
        // Verifica se o método log do LoggerService foi chamado uma vez
        verify(loggerService, times(1)).log(anyString(), anyString());

        // Testa o caso onde não há inscrições para o usuário
        when(inscricaoRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList());
        ResponseEntity<List<Inscricao>> responseNoContent = inscricaoController.buscarInscricoesPorUsuarioId(idUsuario);
        assertEquals(HttpStatus.NO_CONTENT, responseNoContent.getStatusCode());
        verify(loggerService, times(2)).log(anyString(), anyString()); // Deve ser chamado duas vezes, uma para cada cenário
    }
}