package main;

import main.model.Clavier;
import main.model.ClavierAdaptateur;
import main.model.Jeu;
import main.model.Manette;

public class JeuMain {
    public static void main(String[] args) {
        Manette manette = new ClavierAdaptateur(new Clavier()); /* un certain type de manette
         */
        Jeu jeu = new Jeu(manette);
        jeu.mainLoop();
    }
}
