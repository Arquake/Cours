package programme;

import programme.exceptions.MauvaisNombreDeParametresExceptions;
import programme.exceptions.OperationNonSupporteeException;
import programme.models.*;

public class Main {

    public static void main(String[] args) throws MauvaisNombreDeParametresExceptions, OperationNonSupporteeException {
        Operation chaineOperations = new Addition(new Soustraction(new Multiplication(new Division((new Racine(new Cosinus(null)))))));

        System.out.println(chaineOperations.compute(Operation.Operations.RACINE, 16));

    }
}
