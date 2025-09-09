package org.example;

import org.example.exceptions.ExceptionCalculUnprocessable;
import org.example.exceptions.ExceptionNotEnoughArguments;
import org.example.exceptions.ExceptionTooManyArguments;

import java.util.Arrays;

public class CalculImpl implements Calcul {

    @Override
    public double calculer(double ... args) throws ExceptionTooManyArguments, ExceptionNotEnoughArguments, ExceptionCalculUnprocessable {
        if (args.length > 1) {
            throw new ExceptionTooManyArguments();
        } else if (args.length == 0) {
            throw new ExceptionNotEnoughArguments();
        }
        double result = Math.sqrt(1/(Math.pow(args[0],2)-1));
        if (Double.isNaN(result)) {
            throw new ExceptionCalculUnprocessable();
        }
        return result;
    }
}
