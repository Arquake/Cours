package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class GTest {

    @Spy
    Fibonacci fibo;

    @Spy
    Syracuse syra;

    @Spy
    G g;

    @BeforeEach
    void setUp() {
        fibo = spy(new Fibonacci());
        syra = spy(new Syracuse());
        g = spy(new G());
    }

    @Test
    void bigCutSmallN() throws InvalidValueException {
        doReturn(1L).when(fibo).calculer(1);
        doReturn(4L).when(syra).calculer(1);
        //doReturn(4).when(g).calculer(1,4, Cut.BIG_CUT);

        long resFibo = fibo.calculer(1);
        long resSyra = syra.calculer(1);
        Assertions.assertEquals(5, g.calculer(resFibo, resSyra, Cut.BIG_CUT));
    }

    @Test
    void smallCutSmallN() throws InvalidValueException {
        doReturn(1L).when(fibo).calculer(1);
        doReturn(4L).when(syra).calculer(1);
        //doReturn(Integer.toUnsignedLong(4)).when(g).calculer(1,4, Cut.SMALL_CUT);

        long resFibo = fibo.calculer(1);
        long resSyra = syra.calculer(1);
        Assertions.assertEquals(4, g.calculer(resFibo, resSyra, Cut.SMALL_CUT));
    }

    @Test
    void bigCutBigN() throws InvalidValueException {
        doReturn(89L).when(fibo).calculer(10);
        doReturn(Integer.toUnsignedLong(4)).when(syra).calculer(10);
        //doReturn(Integer.toUnsignedLong(89)).when(g).calculer(89,4, Cut.BIG_CUT);

        long resFibo = fibo.calculer(10);
        long resSyra = syra.calculer(10);
        Assertions.assertEquals(89, g.calculer(resFibo, resSyra, Cut.BIG_CUT));
    }

    @Test
    void smallCutBigN() throws InvalidValueException {
        doReturn(Integer.toUnsignedLong(89)).when(fibo).calculer(10);
        doReturn(Integer.toUnsignedLong(4)).when(syra).calculer(10);
        //doReturn(Integer.toUnsignedLong(89)).when(g).calculer(89,4, Cut.SMALL_CUT);

        long resFibo = fibo.calculer(10);
        long resSyra = syra.calculer(10);
        Assertions.assertEquals(89, g.calculer(resFibo, resSyra, Cut.SMALL_CUT));
    }
}