package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonnachiTest {

    Fibonnachi fibo;

    @BeforeEach
    void init() {
        fibo = new Fibonnachi();
    }

    @Test
    void invalidN() {
        Assertions.assertThrows(InvalidvalueException.class, ()->fibo.getValue(-1));
    }

    @Test
    void n0() throws InvalidvalueException {
        Assertions.assertEquals(1, fibo.getValue(0));
    }

    @Test
    void n1() throws InvalidvalueException {
        Assertions.assertEquals(1, fibo.getValue(1));
    }

    @Test
    void above1() throws InvalidvalueException {
        Assertions.assertTrue(fibo.getValue(6)>1);
    }

}