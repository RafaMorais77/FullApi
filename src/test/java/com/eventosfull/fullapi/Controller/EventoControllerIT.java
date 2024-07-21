package com.eventosfull.fullapi.Controller;

import com.eventosfull.fullapi.model.Eventos;
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
public class EventoControllerIT {
    
    public EventoControllerIT() {
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
     * Test of listaEventos method, of class EventoController.
     */
    @Test
    public void testListaEventos() {
        System.out.println("listaEventos");
        EventoController instance = new EventoController();
        ResponseEntity<List<Eventos>> expResult = null;
        ResponseEntity<List<Eventos>> result = instance.listaEventos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionar method, of class EventoController.
     */
    @Test
    public void testAdicionar() {
        System.out.println("adicionar");
        Eventos evento = null;
        EventoController instance = new EventoController();
        Eventos expResult = null;
        Eventos result = instance.adicionar(evento);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarEventoPorId method, of class EventoController.
     */
    @Test
    public void testBuscarEventoPorId() {
        System.out.println("buscarEventoPorId");
        Long id = null;
        EventoController instance = new EventoController();
        ResponseEntity<Eventos> expResult = null;
        ResponseEntity<Eventos> result = instance.buscarEventoPorId(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
