package veras.fr.tp3.exceptions;

public class LoginDejaUtiliseException extends Exception {
    public LoginDejaUtiliseException() {
        super("Le login est déjà pris");
    }
}
