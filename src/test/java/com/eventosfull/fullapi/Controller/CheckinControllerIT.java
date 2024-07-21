/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.eventosfull.fullapi.Controller;

import com.eventosfull.fullapi.model.Checkin;
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
public class CheckinControllerIT {
    
    public CheckinControllerIT() {
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
     * Test of listaCheckin method, of class CheckinController.
     */
    @Test
    public void testListaCheckin() {
        System.out.println("listaCheckin");
        CheckinController instance = new CheckinController();
        ResponseEntity<List<Checkin>> expResult = null;
        ResponseEntity<List<Checkin>> result = instance.listaCheckin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionar method, of class CheckinController.
     */
    @Test
    public void testAdicionar() {
        System.out.println("adicionar");
        Checkin checkin = null;
        CheckinController instance = new CheckinController();
        Checkin expResult = null;
        Checkin result = instance.adicionar(checkin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
