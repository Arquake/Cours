package programme.handlers;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.Addition;
import programme.models.Operation;

public class HandleDivision extends Handler {

    public HandleDivision(Handler h) {
        super(
                values -> values.length == 2,
                values -> values[0] / values[1],
                OperationsType.ADDITION,
                h
        );
    }

    public HandleDivision() {
        super(
                values -> values.length == 2,
                values -> values[0] / values[1],
                OperationsType.ADDITION
        );
    }
}
