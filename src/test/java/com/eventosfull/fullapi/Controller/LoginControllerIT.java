package com.eventosfull.fullapi.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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

public class LoginControllerIT {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LoggerService loggerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        Usuarios usuario = new Usuarios();
        usuario.setLogin("user1");
        usuario.setSenha("password1");

        Usuarios usuarioEncontrado = new Usuarios();
        usuarioEncontrado.setLogin("user1");
        usuarioEncontrado.setSenha("password1");

        when(usuarioRepository.findByLogin("user1")).thenReturn(usuarioEncontrado);

        ResponseEntity<String> response = loginController.login(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login bem-sucedido", response.getBody());
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testLoginFailure() {
        Usuarios usuario = new Usuarios();
        usuario.setLogin("user1");
        usuario.setSenha("wrongpassword");

        Usuarios usuarioEncontrado = new Usuarios();
        usuarioEncontrado.setLogin("user1");
        usuarioEncontrado.setSenha("password1");

        when(usuarioRepository.findByLogin("user1")).thenReturn(usuarioEncontrado);

        ResponseEntity<String> response = loginController.login(usuario);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Login ou senha incorretos", response.getBody());
        verify(loggerService, times(1)).log(anyString(), anyString());
    }

    @Test
    public void testLoginUserNotFound() {
        Usuarios usuario = new Usuarios();
        usuario.setLogin("user1");
        usuario.setSenha("password1");

        when(usuarioRepository.findByLogin("user1")).thenReturn(null);

        ResponseEntity<String> response = loginController.login(usuario);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Login ou senha incorretos", response.getBody());
        verify(loggerService, times(1)).log(anyString(), anyString());
    }
}
