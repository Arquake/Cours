package main.model;

public class Jeu {
    private Manette manette;
    public Jeu(Manette manette) { this.manette = manette; }
    public void mainLoop() { /* boucle principale du jeu */
        if(manette.isLeft()) { /* déplacement à gauche */ }
        else if(manette.isRight()) { /* déplacement à droite */ }
        else if(manette.isPush()) { /* appui sur le bouton */}
    }
}