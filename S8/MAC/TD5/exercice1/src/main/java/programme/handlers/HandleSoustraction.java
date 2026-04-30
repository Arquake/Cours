package programme.handlers;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.Addition;
import programme.models.Operation;

public class HandleSoustraction extends Handler {

    public HandleSoustraction(Handler h) {
        super(
                values -> values.length == 2,
                values -> values[0] - values[1],
                OperationsType.SOUSTRACTION,
                h
        );
    }

    public HandleSoustraction() {
        super(
                values -> values.length == 2,
                values -> values[0] - values[1],
                OperationsType.SOUSTRACTION
        );
    }
}
