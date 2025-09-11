package org.example;

import org.example.interfaces.TriTableau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public abstract class AbstractTriTableauTest {

    private static TriTableau instance;

    @BeforeEach
    public void setup() {
        instance = this.getInstance();
    }
    abstract TriTableau getInstance();

    @Test
    public void testTri() {
        Integer[] i = {6,7,23,1,56,9,6,4,23,99};
        Integer[] res = instance.trier(i);
        assert(Arrays.equals(res, Arrays.stream(i).sorted().toArray()));
    }

    @Test
    public void emptyArray() {
        Integer[] i = {};
        Assertions.assertDoesNotThrow(()->instance.trier(i));
    }
}
