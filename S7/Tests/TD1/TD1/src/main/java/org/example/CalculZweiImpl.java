package org.example;

import org.example.exceptions.ExceptionCalculUnprocessable;
import org.example.exceptions.ExceptionNotEnoughArguments;
import org.example.exceptions.ExceptionTooManyArguments;
import org.example.interfaces.Calcul;

public class CalculZweiImpl implements Calcul {

    /**
     * Prends 3 paramètre {x, y, z} et retourne le résultat de "Math.sqrt(1/(y*(z-1)/(Math.pow(x, 2)-1)))"
     * @param args {x, y, z}
     * @return Math.sqrt(1/(y*(z-1)/(Math.pow(x, 2)-1)))
     * @throws ExceptionTooManyArguments si il y a plus de 3 arguments donnés
     * @throws ExceptionNotEnoughArguments si il y a moins de 3 arguments donnés
     * @throws ExceptionCalculUnprocessable si le calcul ne peut pas effectuer dû à des contraintes de calcul
     */
    @Override
    public double calculer(double... args) throws ExceptionTooManyArguments, ExceptionNotEnoughArguments, ExceptionCalculUnprocessable {
        if (args.length > 3) {
            throw new ExceptionTooManyArguments();
        } else if (args.length < 3) {
            throw new ExceptionNotEnoughArguments();
        }
        double result = Math.sqrt(1/(args[1]*(args[2]-1)/(Math.pow(args[0], 2)-1)));
        if (Double.isNaN(result)) {
            throw new ExceptionCalculUnprocessable();
        }
        return result;
    }
}
