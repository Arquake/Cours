package fr.miage.orleans.mac;

public class Pommier extends ArbreFruitier {
    public Pommier() {
        super("Pommier");
    }

    @Override
    public Fruit cueille() {
        return FruitFactory.makePomme();
    }
}
