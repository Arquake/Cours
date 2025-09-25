package modele.operations;


public class Soustraction extends Operations {


    public Soustraction(Operations next) {
        super("Soustraction", next);
    }

    @Override
    protected double getResultat(double operande1, double operande2) {
        return operande1-operande2;
    }
}
