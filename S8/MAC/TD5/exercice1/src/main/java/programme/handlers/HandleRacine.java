package programme.handlers;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.Addition;
import programme.models.Operation;

public class HandleRacine extends Handler {

    public HandleRacine(Handler h) {
        super(
                values -> values.length == 1,
                values -> Math.sqrt(values[0]),
                OperationsType.RACINE,
                h
        );
    }

    public HandleRacine() {
        super(
                values -> values.length == 1,
                values -> Math.sqrt(values[0]),
                OperationsType.RACINE
        );
    }
}
