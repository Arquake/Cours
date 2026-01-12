package fr.miage.orleans.mac;

import java.util.Objects;

public class AnimalFactory {

    private static Chien chien;
    private static Chat chat;

    public static Chien makeDog() {
        if (Objects.isNull(chien)) {
            chien = new Chien();
        }
        return chien;
    }

    public static Chat makeCat() {
        if (Objects.isNull(chat)) {
            chat = new Chat();
        }
        return chat;
    }
}
