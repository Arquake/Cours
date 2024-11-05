package paj.tp3.rmi.exceptions.Exceptions;

public class AlreadyContributedException extends Exception{

    public AlreadyContributedException() {
        super("The user already participated in the poll");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
