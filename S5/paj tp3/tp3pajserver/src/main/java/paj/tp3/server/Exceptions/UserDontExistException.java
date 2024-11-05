package paj.tp3.server.Exceptions;

public class UserDontExistException extends Exception {
    public UserDontExistException() {
        super("Server.Interfaces.User don't exist");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
