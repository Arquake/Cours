package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class rechercheTableauTrieTest {

    private rechercheTableauTrie facade;

    @BeforeEach
    void init() {
        facade = new rechercheTableauTrie();
    }

    @Test
    void minEqualMax() {

        boolean b = facade.rechercheDico(int[], 10);
    }

}