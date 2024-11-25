package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testSquare() {
        App app = new App(); // Utilise le constructeur sans paramètre
        int result = app.square(5);
        assertEquals(25, result, "Le carré de 5 devrait être 25");
    }

    @Test
    public void testGetAppName() {
        App app = new App("TestApp");
        assertEquals("TestApp", app.getAppName(), "Le nom de l'application devrait être 'TestApp'");
    }
}
