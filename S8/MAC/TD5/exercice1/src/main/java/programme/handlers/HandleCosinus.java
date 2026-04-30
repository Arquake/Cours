package programme.handlers;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.Addition;
import programme.models.Operation;

public class HandleCosinus extends Handler{

    public HandleCosinus(Handler h) {
        super(
                values -> values.length == 1,
                values -> values[0],
                OperationsType.COSINUS,
                h
        );
    }

    public HandleCosinus() {
        super(
                values -> values.length == 1,
                values -> values[0],
                OperationsType.COSINUS
        );
    }
}
