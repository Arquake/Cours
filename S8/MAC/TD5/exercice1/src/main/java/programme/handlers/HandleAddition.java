package programme.handlers;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.Addition;
import programme.models.Operation;

public class HandleAddition extends Handler {

    public HandleAddition(Handler h) {
        super(
                values -> values.length == 2,
                values -> values[0] + values[1],
                OperationsType.ADDITION,
                h
        );
    }

    public HandleAddition() {
        super(
                values -> values.length == 2,
                values -> values[0] + values[1],
                OperationsType.ADDITION
        );
    }
}
