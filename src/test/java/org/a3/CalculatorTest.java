package org.a3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final CalculatorService service = new CalculatorService();

    @Test
    void add() {
        assertEquals(6.0, service.add(2.5, 3.5));
        assertEquals(-5, service.add(-2, -3));
        assertEquals(-1, service.add(2, -3));
        assertEquals(5, service.add(0, 5));
    }

    @Test
    void subtract() {
        assertEquals(3.0, service.subtract(5.5, 2.5));
        assertEquals(-3.0, service.subtract(-5.5, -2.5));
        assertEquals(-3, service.subtract(-5, -2));
        assertEquals(-3, service.subtract(2, 5));
        assertEquals(5, service.subtract(5, 0));
    }

    @Test
    void multiply() {
        assertEquals(6, service.multiply(-2, -3));
        assertEquals(-6, service.multiply(2, -3));
        assertEquals(8.75, service.multiply(2.5, 3.5));
        assertEquals(8.75, service.multiply(-2.5, -3.5));
    }

    @Test
    void divide() {
        assertEquals(3, service.divide(-6, -2));
        assertEquals(5.5, service.divide(5.5, 1.0));
        assertEquals(0.0, service.divide(0.0, 5.5));
        assertEquals(0, service.divide(0, 5));
        assertThrows(ArithmeticException.class, () -> service.divide(5, 0));
    }
}