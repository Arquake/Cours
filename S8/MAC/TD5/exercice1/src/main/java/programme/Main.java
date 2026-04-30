package programme;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.handlers.*;
import programme.models.*;

public class Main {

    public static void main(String[] args) throws MauvaisNombreDeParametresExceptions, OperationNonSupporteeException {
        Handler chaineOperations = new HandleAddition(new HandleSoustraction(new HandleMultiplication(new HandleDivision((new HandleRacine(new HandleCosinus(null)))))));

        System.out.println(chaineOperations.handle(Handler.OperationsType.RACINE, 16d));

    }
}
