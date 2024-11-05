package paj.tp3.rmi.exceptions.Exceptions;

public class EmailAlreadyInUseException extends Exception{
    public EmailAlreadyInUseException() {
        super("Email déjà pris");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}