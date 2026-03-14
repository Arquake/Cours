package programme.models;

import java.util.function.Function;
import java.util.function.Predicate;

public class Multiplication extends Operation {
    public Multiplication(Operation successor) {
        super(
                successor,
                Operations.MULTIPLICATION,
                values -> values.length == 2,
                values -> values[0] * values[1]
        );
    }
}
