package fr.miage.orleans.mac;

public class Poirier extends ArbreFruitier {
    public Poirier() {
        super("Poirier");
    }

    @Override
    public Fruit cueille() {
        return FruitFactory.makePoire();
    }
}
