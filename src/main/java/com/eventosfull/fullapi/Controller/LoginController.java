package com.eventosfull.fullapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventosfull.fullapi.model.Usuarios;
import com.eventosfull.fullapi.repository.UsuarioRepository;
import com.eventosfull.fullapi.service.LoggerService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoggerService loggerService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Usuarios usuario) {
        Usuarios usuarioEncontrado = usuarioRepository.findByLogin(usuario.getLogin());

        if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(usuario.getSenha())) {
            loggerService.log("Login bem-sucedido", "Usuário logado: " + usuarioEncontrado.getLogin());
            return ResponseEntity.ok("Login bem-sucedido");
        } else {
            loggerService.log("Tentativa de login falhou", "Login ou senha incorretos para o usuário: " + usuario.getLogin());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha incorretos");
        }
    }
}
