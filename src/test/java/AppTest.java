package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void testSquare() {
        App app = new App("TestApp");
        assertEquals(25, app.square(5), "Le carré de 5 doit être égal à 25");
    }

    @Test
    void testAppName() {
        App app = new App("TestApp");
        assertEquals("TestApp", app.getAppName(), "Le nom de l'application doit être 'TestApp'");
    }
}
