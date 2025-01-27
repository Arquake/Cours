package modele.operations;


public class Division extends Operations {


    public Division(Operations next) {
        super("Division", next);
    }

    @Override
    protected double getResultat(double operande1, double operande2) {
        return operande1/operande2;
    }
}
