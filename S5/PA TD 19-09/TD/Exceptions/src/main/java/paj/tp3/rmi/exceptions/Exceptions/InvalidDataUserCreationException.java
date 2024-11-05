package paj.tp3.rmi.exceptions.Exceptions;


public class InvalidDataUserCreationException extends Exception {
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public InvalidDataUserCreationException() {
        super("Les donées sont invalide");

    }
}
