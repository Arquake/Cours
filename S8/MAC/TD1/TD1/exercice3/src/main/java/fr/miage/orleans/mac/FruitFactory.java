package fr.miage.orleans.mac;

public class FruitFactory {

    public static Fruit makePomme() {
        return new Fruit("pomme");
    }

    public static Fruit makePoire() {
        return new Fruit("poire");
    }
}
