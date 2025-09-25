package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td2Exo6.AlgoTri;

import java.util.ArrayList;
import java.util.List;

class AlgoTriTest {

    private List<Integer> l;

    private List<Integer> lRes;

    public static td2Exo6.AlgoTri<Integer> algo;

    @BeforeAll
    static void beforeAll() {
        algo = new AlgoTri<Integer>();
    }

    @BeforeEach
    void setUp() {
        l = new ArrayList<>();

        lRes = new ArrayList<>();
    }

    @Test
    void testEmpty() {

        l = algo.tri(l);

        Assertions.assertEquals(lRes, l);
    }

    @Test
    void oneElement() {
        l.add(1);

        lRes.add(1);

        l = algo.tri(l);

        Assertions.assertEquals(lRes, l);
    }

    @Test
    void noChanges() {
        l.add(1);
        l.add(4);
        l.add(5);
        l.add(6);

        lRes.add(1);
        lRes.add(4);
        lRes.add(5);
        lRes.add(6);

        l = algo.tri(l);

        Assertions.assertEquals(lRes, l);
    }

    @Test
    void haveChanges() {
        l.add(5);
        l.add(1);
        l.add(4);
        l.add(6);

        lRes.add(1);
        lRes.add(4);
        lRes.add(5);
        lRes.add(6);

        l = algo.tri(l);

        Assertions.assertEquals(lRes, l);
    }

    @Test
    void nullInList() {
        l.add(5);
        l.add(1);
        l.add(null);
        l.add(6);

        lRes.add(1);
        lRes.add(4);
        lRes.add(null);
        lRes.add(6);

        l = algo.tri(l);

        Assertions.assertEquals(lRes, l);
    }

    @Test
    void duplicates() {
        l.add(5);
        l.add(5);
        l.add(5);
        l.add(5);

        lRes.add(5);

        l = algo.tri(l);

        System.out.println(l);
        System.out.println(lRes);

        Assertions.assertEquals(lRes, l);
    }
}