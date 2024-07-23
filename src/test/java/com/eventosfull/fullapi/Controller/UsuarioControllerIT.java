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

import com.eventosfull.fullapi.model.Usuarios;
import com.eventosfull.fullapi.repository.UsuarioRepository;
import com.eventosfull.fullapi.service.LoggerService;

public class UsuarioControllerIT {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LoggerService loggerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListaUsuarios() {
        List<Usuarios> usuarios = Arrays.asList(new Usuarios(), new Usuarios());

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        ResponseEntity<List<Usuarios>> response = usuarioController.listaUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testAdicionar() {
        Usuarios usuario = new Usuarios();
        usuario.setNome("Test User");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuarios result = usuarioController.adicionar(usuario);

        assertEquals("Test User", result.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testBuscarUsuarioPorId() {
        Long id = 1L;
        Usuarios usuario = new Usuarios();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuarios> response = usuarioController.buscarUsuarioPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
        verify(loggerService, times(1)).log(anyString(), anyString());

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<Usuarios> responseNotFound = usuarioController.buscarUsuarioPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
        verify(loggerService, times(2)).log(anyString(), anyString());
    }
}
