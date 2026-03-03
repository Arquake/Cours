package fr.miage.orleans.mac;

import java.util.function.Supplier;

public abstract class ArbreFruitier {
    private String type;
    Supplier<Fruit> cueilleur;

    public ArbreFruitier(String type, Supplier<Fruit> cueilleur) {
        this.type = type;
        this.cueilleur = cueilleur;
    }

    public String toString() {
        return this.type;
    }

    public Fruit cueille() {
        return cueilleur.get();
    }
}
