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
public class InscricaoControllerIT {
    
    public InscricaoControllerIT() {
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


    @Test
    public void testListaUsuarios() {
        System.out.println("listaUsuarios");
        InscricaoController instance = new InscricaoController();
        ResponseEntity<List<Inscricao>> expResult = null;
        ResponseEntity<List<Inscricao>> result = instance.listaUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testAdicionar() {
        System.out.println("adicionar");
        Inscricao inscricao = null;
        InscricaoController instance = new InscricaoController();
        Inscricao expResult = null;
        Inscricao result = instance.adicionar(inscricao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscarInscricaoPorId() {
        System.out.println("buscarInscricaoPorId");
        Long id = null;
        InscricaoController instance = new InscricaoController();
        ResponseEntity<Inscricao> expResult = null;
        ResponseEntity<Inscricao> result = instance.buscarInscricaoPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testDeletarInscricaoPorId() {
        System.out.println("deletarInscricaoPorId");
        Long id = null;
        InscricaoController instance = new InscricaoController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deletarInscricaoPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarInscricoesPorUsuarioId method, of class InscricaoController.
     */
    @Test
    public void testBuscarInscricoesPorUsuarioId() {
        System.out.println("buscarInscricoesPorUsuarioId");
        Long id_usuario = null;
        InscricaoController instance = new InscricaoController();
        ResponseEntity<List<Inscricao>> expResult = null;
        ResponseEntity<List<Inscricao>> result = instance.buscarInscricoesPorUsuarioId(id_usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
