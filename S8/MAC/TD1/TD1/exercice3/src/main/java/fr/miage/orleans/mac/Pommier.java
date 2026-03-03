package fr.miage.orleans.mac;

import java.util.function.Supplier;

public class Pommier extends ArbreFruitier {
    public Pommier(Supplier<Fruit> cueilleur) {
        super("Pommier", cueilleur);
    }
}
