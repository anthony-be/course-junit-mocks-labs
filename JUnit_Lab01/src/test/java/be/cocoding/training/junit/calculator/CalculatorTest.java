package be.cocoding.training.junit.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAdd() {
        assertEquals(15, new Calculator().add(10, 5));
    }

    @Test
    public void testSubstract() {
        assertEquals(5, new Calculator().substract(10, 5));
    }

    @Test
    public void testMultiply() {
        assertEquals(50, new Calculator().multiply(10, 5));
    }

    @Test
    public void testDivide() {
        assertEquals(2, new Calculator().divide(10, 5));
    }
}