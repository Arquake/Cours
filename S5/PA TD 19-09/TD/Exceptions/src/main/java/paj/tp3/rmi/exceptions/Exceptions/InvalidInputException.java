package paj.tp3.rmi.exceptions.Exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("Invalid input value");
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
