package paj.tp3.server.Exceptions;

public class PollDontExistException extends Exception {

    public PollDontExistException() {
        super("Le sondage n'existe pas");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
