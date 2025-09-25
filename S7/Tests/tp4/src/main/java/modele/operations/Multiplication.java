package modele.operations;


public class Multiplication extends Operations {


    public Multiplication(Operations next) {
        super("Multiplication", next);
    }

    @Override
    protected double getResultat(double operande1, double operande2) {
        return operande1*operande2;
    }
}
