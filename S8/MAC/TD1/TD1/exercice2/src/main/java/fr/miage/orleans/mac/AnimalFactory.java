package fr.miage.orleans.mac;

import java.util.Objects;

public class AnimalFactory implements IAnimalFactory {

    private Chien chien;
    private Chat chat;

    public AnimalFactory() {
        this.chien = null;
        this.chat = null;
    }

    private Animal makeDog() {
        if (Objects.isNull(chien)) {
            chien = new Chien();
            return chien;
        }
        return chien.clone();
    }

    private Animal makeCat() {
        if (Objects.isNull(chat)) {
            chat = new Chat();
            return chat;
        }
        return chat.clone();
    }

    public Animal makeAnimal(String animal) throws AnimalDoesNotExistException {
        switch (animal) {
            case "chat":
                return makeCat();
            case "chien":
                return makeDog();
            default:
                throw new AnimalDoesNotExistException();
        }
    }
}
