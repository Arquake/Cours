package org.example;

import org.example.exceptions.ExceptionCalculUnprocessable;
import org.example.exceptions.ExceptionNotEnoughArguments;
import org.example.exceptions.ExceptionTooManyArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculZweiImplTest {

    static CalculZweiImpl instance;

    @BeforeAll
    public static void getInstance() {
        instance = new CalculZweiImpl();
    }

    @Test
    public void tooManyValues() {
        double[] values = {3,3,3,3};
        Assertions.assertThrows(ExceptionTooManyArguments.class, ()->instance.calculer(values));
    }

    @Test
    public void notEnoughValues() {
        double[] values = {3,3};
        Assertions.assertThrows(ExceptionNotEnoughArguments.class, ()->instance.calculer(values));
    }

    /**
     * On crée 2 signes négatifs dans la racine carrée
     */
    @Test
    public void xzNegative() {
        double[] values = {0.5, 3, -1};
        Assertions.assertDoesNotThrow(()->instance.calculer(values));
    }

    /**
     * On crée 2 signes négatifs dans la racine carrée
     */
    @Test
    public void xyNegative() {
        double[] values = {0.5, -1, 3};
        Assertions.assertDoesNotThrow(()->instance.calculer(values));
    }

    /**
     * On crée 2 signes négatifs dans la racine carrée
     */
    @Test
    public void yzNegative() {
        double[] values = {3,-1,0.5};
        Assertions.assertDoesNotThrow(()->instance.calculer(values));
    }

    /**
     * On fait un test avec des arguments tous valides
     */
    @Test
    public void allPositive() {
        double[] values = {3,3,3};
        Assertions.assertDoesNotThrow(()->instance.calculer(values));
    }

    /**
     * On fait un test avec des arguments tous négatifs
     */
    @Test
    public void allNegative() {
        double[] values = {0.5,-1,0.5};
        Assertions.assertThrows(ExceptionCalculUnprocessable.class, ()->instance.calculer(values));
    }

    /**
     * On crée 1 signe négatif dans la racine carrée
     */
    @Test
    public void yNegative() {
        double[] values = {3,-1,3};
        Assertions.assertThrows(ExceptionCalculUnprocessable.class, ()->instance.calculer(values));
    }

    /**
     * On crée 1 signe négatif dans la racine carrée
     */
    @Test
    public void zNegative() {
        double[] values = {3,3,0.5};
        Assertions.assertThrows(ExceptionCalculUnprocessable.class, ()->instance.calculer(values));
    }

    /**
     * On crée 1 signe négatif dans la racine carrée
     */
    @Test
    public void xNegative() {
        double[] values = {0.5, 3, 3};
        Assertions.assertThrows(ExceptionCalculUnprocessable.class, ()->instance.calculer(values));
    }
}