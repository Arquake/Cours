package programme;

public class Main {

    public static void main(String[] args) throws MauvaisNombreDeParametresExceptions, OperationNonSupporteeException {
        Operation chaineOperations = new Addition(new Soustraction(new Multiplication(new Division((null)))));

        System.out.println(chaineOperations.compute(Operation.Operations.ADDITION, 12, 30));

    }
}
