package programme.models;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Operation {

    private Operation successor;
    private Operations operations;
    private Predicate<double[]> valuesValidity;
    private Function<double[], Double> calculate;

    public Operation(Operation successor, Operations operations, Predicate<double[]> valuesValidity, Function<double[], Double> calculate) {
        this.successor = successor;
        this.operations = operations;
        this.valuesValidity = valuesValidity;
        this.calculate = calculate;
    }

    public double compute(Operations operations, double ...values) throws OperationNonSupporteeException, MauvaisNombreDeParametresExceptions {
        if (this.operations.equals(operations)) {
            if (valuesValidity.test(values)) return calculate.apply(values);
            throw new MauvaisNombreDeParametresExceptions();
        } else if (Objects.isNull(successor)) {
            throw new OperationNonSupporteeException();
        }
        return successor.compute(operations, values);
    }

    public enum Operations {
        ADDITION,
        MULTIPLICATION,
        DIVISION,
        SOUSTRACTION,
        RACINE,
        COSINUS
    }

}
