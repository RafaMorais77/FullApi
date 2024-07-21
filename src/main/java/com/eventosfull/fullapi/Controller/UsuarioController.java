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
import com.eventosfull.fullapi.model.Usuarios;
import com.eventosfull.fullapi.repository.UsuarioRepository;
import com.eventosfull.fullapi.service.LoggerService; 

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoggerService loggerService; // Injetando o serviço de log

    @GetMapping
    public ResponseEntity<List<Usuarios>> listaUsuarios() {
        List<Usuarios> usuarios = usuarioRepository.findAll();
        loggerService.log("Listagem de Usuários", "Listagem de todos os usuários");
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuarios adicionar(@RequestBody Usuarios usuario) {
        Usuarios novoUsuario = usuarioRepository.save(usuario);
        loggerService.log("Adição de Usuário", "Novo usuário adicionado: " + novoUsuario.toString());
        return novoUsuario;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuarios> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            loggerService.log("Busca de Usuário por ID", "ID do usuário buscado: " + id);
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            loggerService.log("Busca de Usuário por ID", "Usuário com ID " + id + " não encontrado");
            return ResponseEntity.notFound().build();
        }
    }
}
