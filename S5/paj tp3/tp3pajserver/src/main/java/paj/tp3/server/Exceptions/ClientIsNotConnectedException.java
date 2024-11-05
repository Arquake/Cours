package paj.tp3.server.Exceptions;

public class ClientIsNotConnectedException extends Exception{
    public ClientIsNotConnectedException() {
        super("Le client n'est pas connecté");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
