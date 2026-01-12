package fr.miage.orleans.mac.models;

import java.util.Random;

public class Corrige {

    public Corrige() {
    }

    public int getPrice() {
        return new Random().nextInt(1,10000);
    }
}
