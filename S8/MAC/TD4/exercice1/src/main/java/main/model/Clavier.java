package main.model;

import java.util.Random;

public class Clavier {
    public enum Key { SPACEBAR, ARROW_LEFT, ARROW_RIGHT }
    public Clavier() {}
    public Key keyPressed() {
        Random rand = new Random();
        return switch (rand.nextInt(Key.values().length)) {
            case 0 -> Key.SPACEBAR;
            case 1 -> Key.ARROW_LEFT;
            case 2 -> Key.ARROW_RIGHT;
            default -> Key.SPACEBAR;
        };
    }
}