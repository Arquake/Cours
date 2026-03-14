package programme.models;

import java.util.function.Function;
import java.util.function.Predicate;

public class Division extends Operation {

    public Division(Operation successor) {
        super(
                successor,
                Operations.DIVISION,
                values -> values.length == 2,
                values -> values[0] / values[1]
        );
    }
}
