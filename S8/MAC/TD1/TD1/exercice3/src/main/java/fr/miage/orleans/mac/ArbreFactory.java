package fr.miage.orleans.mac;

public class ArbreFactory implements IArbreFactory {

    public ArbreFruitier makePommier() {
        return new Pommier(()->new Fruit("Pomme"));
    }

    public ArbreFruitier makePoirier() {
        return new Pommier(()->new Fruit("Poire"));
    }
}
