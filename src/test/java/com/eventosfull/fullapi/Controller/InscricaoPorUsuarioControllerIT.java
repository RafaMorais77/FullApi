/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.eventosfull.fullapi.Controller;

import com.eventosfull.fullapi.model.Inscricao;
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
public class InscricaoPorUsuarioControllerIT {
    
    public InscricaoPorUsuarioControllerIT() {
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
     * Test of buscarInscricoesPorUsuarioId method, of class InscricaoPorUsuarioController.
     */
    @Test
    public void testBuscarInscricoesPorUsuarioId() {
        System.out.println("buscarInscricoesPorUsuarioId");
        Long id_usuario = null;
        InscricaoPorUsuarioController instance = new InscricaoPorUsuarioController();
        ResponseEntity<List<Inscricao>> expResult = null;
        ResponseEntity<List<Inscricao>> result = instance.buscarInscricoesPorUsuarioId(id_usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletarInscricaoPorIdUsuario method, of class InscricaoPorUsuarioController.
     */
    @Test
    public void testDeletarInscricaoPorIdUsuario() {
        System.out.println("deletarInscricaoPorIdUsuario");
        Long id_usuario = null;
        InscricaoPorUsuarioController instance = new InscricaoPorUsuarioController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deletarInscricaoPorIdUsuario(id_usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
