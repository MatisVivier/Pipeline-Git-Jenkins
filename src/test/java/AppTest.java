package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void testAddition() {
        App app = new App();
        assertEquals(5, app.addition(2, 3), "L'addition de 2 et 3 doit être égale à 5");
    }
}
