package fr.miage.orleans.mac;

public class AnimalDoesNotExistException extends Exception {

    public AnimalDoesNotExistException() {
        super("L'animal n'existe pas");
    }
}
