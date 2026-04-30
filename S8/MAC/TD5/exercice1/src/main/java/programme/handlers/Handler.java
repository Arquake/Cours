package programme.handlers;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.Operation;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Handler {

    Handler nextHandler;
    Predicate<Double[]> valuesValidity;
    OperationsType type;
    Function<Double[], Double> calculate;

    Handler(Predicate<Double[]> valuesValidity, Function<Double[], Double> calculate, OperationsType type) {
        this.valuesValidity = valuesValidity;
        this.calculate = calculate;
        this.type = type;
        nextHandler = null;
    }

    Handler(Predicate<Double[]> valuesValidity, Function<Double[], Double> calculate, OperationsType type, Handler h) {
        this.valuesValidity = valuesValidity;
        this.calculate = calculate;
        this.type = type;
        nextHandler = h;
    }

    public void setNextHandler(Handler handler) {
        nextHandler = handler;
    }

    public double handle(OperationsType o, Double ...values) throws MauvaisNombreDeParametresExceptions, OperationNonSupporteeException {
        if (this.type.equals(o)) {
            if (valuesValidity.test(values)) return calculate.apply(values);
            throw new MauvaisNombreDeParametresExceptions();
        } else if (Objects.isNull(nextHandler)) {
            throw new OperationNonSupporteeException();
        }
        return nextHandler.handle(o, values);
    }


    public enum OperationsType {
        ADDITION,
        MULTIPLICATION,
        DIVISION,
        SOUSTRACTION,
        RACINE,
        COSINUS
    }
}
