package com.eventosfull.fullapi.Controller;

import com.eventosfull.fullapi.model.Usuarios;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Rafae
 */
public class UsuarioControllerIT {
    
    public UsuarioControllerIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of listaUsuarios method, of class UsuarioController.
     */
    @Test
    public void testListaUsuarios() {
        System.out.println("listaUsuarios");
        UsuarioController instance = new UsuarioController();
        ResponseEntity<List<Usuarios>> expResult = null;
        ResponseEntity<List<Usuarios>> result = instance.listaUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionar method, of class UsuarioController.
     */
    @Test
    public void testAdicionar() {
        System.out.println("adicionar");
        Usuarios usuario = null;
        UsuarioController instance = new UsuarioController();
        Usuarios expResult = null;
        Usuarios result = instance.adicionar(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarUsuarioPorId method, of class UsuarioController.
     */
    @Test
    public void testBuscarUsuarioPorId() {
        System.out.println("buscarUsuarioPorId");
        Long id = null;
        UsuarioController instance = new UsuarioController();
        ResponseEntity<Usuarios> expResult = null;
        ResponseEntity<Usuarios> result = instance.buscarUsuarioPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
