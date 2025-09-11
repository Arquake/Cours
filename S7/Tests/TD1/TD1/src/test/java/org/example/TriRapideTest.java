package org.example;

import org.example.interfaces.TriTableau;

import static org.junit.jupiter.api.Assertions.*;

class TriRapideTest extends AbstractTriTableauTest{

    @Override
    TriTableau getInstance() {
        return new TriRapide();
    }
}