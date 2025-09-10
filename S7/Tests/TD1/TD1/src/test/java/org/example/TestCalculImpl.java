package org.example;

import org.example.exceptions.ExceptionCalculUnprocessable;
import org.example.exceptions.ExceptionNotEnoughArguments;
import org.example.exceptions.ExceptionTooManyArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class TestCalculImpl {

    static CalculImpl instance;

    @BeforeAll
    public static void getInstance() {
        instance = new CalculImpl();
    }

    @Test
    public void tooManyArgumentsTets() {
        double[] values = {1, 1};
        Assertions.assertThrows(ExceptionTooManyArguments.class , () -> instance.calculer(values));
    }

    @Test
    public void notEnoughArguments() {
        double[] values = {};
        Assertions.assertThrows(ExceptionNotEnoughArguments.class , () -> instance.calculer(values));
    }

    @Test
    public void impossibleValue() {
        double[] values = {0};
        Assertions.assertThrows(ExceptionCalculUnprocessable.class , () -> instance.calculer(values));
    }

    @Test
    public void validTest() {
        double[] values = {10};
        Assertions.assertDoesNotThrow(()->instance.calculer(values));
    }
}
