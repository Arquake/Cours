package fr.miage.orleans.mac;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Poirier extends ArbreFruitier {



    public Poirier(Supplier<Fruit> cueilleur) {
        super("Poirier", cueilleur);
    }
}
