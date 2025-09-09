package org.example;

import org.example.exceptions.ExceptionCalculUnprocessable;
import org.example.exceptions.ExceptionNotEnoughArguments;
import org.example.exceptions.ExceptionTooManyArguments;

public interface Calcul {
    double calculer(double ... args) throws ExceptionTooManyArguments, ExceptionNotEnoughArguments, ExceptionCalculUnprocessable;
}
