package fr.miage.orleans.mac;

public abstract class ArbreFruitier {
    private String type;

    public ArbreFruitier(String type) {
        this.type = type;
    }

    public abstract Fruit cueille();

    public String toString() {
        return this.type;
    }
}
