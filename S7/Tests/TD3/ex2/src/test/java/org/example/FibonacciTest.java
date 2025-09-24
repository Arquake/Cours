package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class FibonacciTest {

    Fibonacci fibo;

    @BeforeEach
    void setUp() {
        fibo = spy(new Fibonacci());
    }

    @Test
    void fibo0() throws InvalidValueException {
        //doReturn(Integer.toUnsignedLong(1)).when(fibo).calculer(0);

        Assertions.assertEquals(1, fibo.calculer(0));
    }

    @Test
    void fibo1() throws InvalidValueException {
        //doReturn(Integer.toUnsignedLong(1)).when(fibo).calculer(1);

        Assertions.assertEquals(1, fibo.calculer(1));
    }

    @Test
    void fibo10() throws InvalidValueException {
        //doReturn(Integer.toUnsignedLong(89)).when(fibo).calculer(10);

        Assertions.assertEquals(89, fibo.calculer(10));
    }

    @Test
    void fibo100() throws InvalidValueException {
        //doReturn(Integer.toUnsignedLong(89)).when(fibo).calculer(10);
        Assertions.assertEquals(1_298_777_728_820_984_005L, fibo.calculer(100));
    }
}