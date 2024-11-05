package paj.tp3.server.Exceptions;

public class EmailAlreadyInUseException extends Exception{
    public EmailAlreadyInUseException() {
        super("Email déjà pris");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
