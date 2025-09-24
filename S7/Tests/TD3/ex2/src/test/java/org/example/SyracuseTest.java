package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class SyracuseTest {

    Syracuse syra;

    @BeforeEach
    void setUp() {
        syra = spy(new Syracuse());
    }

    @Test
    void at0() {
        //doReturn(Integer.toUnsignedLong(1)).when(syra).calculer(0);

        Assertions.assertEquals(1, syra.calculer(0));
    }

    @Test
    void at1() {
        //doReturn(Integer.toUnsignedLong(4)).when(syra).calculer(1);

        Assertions.assertEquals(4, syra.calculer(1));
    }

    @Test
    void at10() {
        //doReturn(Integer.toUnsignedLong(4)).when(syra).calculer(10);

        Assertions.assertEquals(4, syra.calculer(10));
    }
}