package modele.operations;


public class Somme extends Operations {


    public Somme(Operations next) {
        super("Somme", next);
    }

    @Override
    protected double getResultat(double operande1, double operande2) {
        return operande1+operande2;
    }
}
