package programme.models;

import java.util.function.Function;
import java.util.function.Predicate;

public class Addition extends Operation {
    public Addition(Operation successor) {
        super(
                successor,
                Operations.ADDITION,
                values -> values.length == 2,
                values -> values[0] + values[1]
        );
    }
}
