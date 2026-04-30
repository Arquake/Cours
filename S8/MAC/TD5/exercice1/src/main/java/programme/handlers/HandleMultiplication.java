package programme.handlers;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.Addition;
import programme.models.Operation;

public class HandleMultiplication extends Handler {

    public HandleMultiplication(Handler h) {
        super(
                values -> values.length == 2,
                values -> values[0] * values[1],
                OperationsType.MULTIPLICATION,
                h
        );
    }

    public HandleMultiplication() {
        super(
                values -> values.length == 2,
                values -> values[0] * values[1],
                OperationsType.MULTIPLICATION
        );
    }
}
