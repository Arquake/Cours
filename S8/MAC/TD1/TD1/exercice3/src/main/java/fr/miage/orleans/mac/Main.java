package fr.miage.orleans.mac;

public class Main {
    public static void main(String[] args) {
        ArbreFruitier pommier = new Pommier();
        ArbreFruitier poirier = new Poirier();
        Hobbit maraudeur = new Hobbit("Merry");
        maraudeur.mange(pommier.cueille());
        maraudeur.mange(poirier.cueille());
    }
}
