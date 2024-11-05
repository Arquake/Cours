package paj.tp3.rmi.exceptions.Exceptions;

public class ChoiceDontExistException extends Exception {
    public ChoiceDontExistException() {
        super("Le choix n'existe pas");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
