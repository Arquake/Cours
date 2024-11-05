package paj.tp3.rmi.exceptions.Exceptions;

public class UuidAlreadyInUseException extends Exception{

    public UuidAlreadyInUseException(){
        super("Uuid is lready used please take another one");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
