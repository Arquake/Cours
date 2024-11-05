package paj.tp3.server;

import Exceptions.InvalidInputException;

import java.util.Scanner;

public abstract class AbstractUser {

    private final boolean isAuth;

    AbstractUser(boolean authState) {
        isAuth = authState;
    }

    protected String getUserInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected void verifyValidityChoice(String values) throws InvalidInputException {
        Scanner scanner = new Scanner(values);

        if(!scanner.hasNextInt()) {
            throw new InvalidInputException();
        }
        scanner.next();
        if (scanner.hasNext()) {
            throw new InvalidInputException();
        }
    }

    public boolean isAuthenticated() {
        return isAuth;
    };
}
