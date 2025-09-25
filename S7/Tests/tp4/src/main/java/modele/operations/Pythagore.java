package modele.operations;


public class Pythagore extends Operations {


    public Pythagore( Operations next) {
        super("Pythagore", next);
    }

    @Override
    protected double getResultat(double operande1, double operande2) {
        return Math.sqrt(Math.pow(operande1,2)+Math.pow(operande2,2));
    }
}
