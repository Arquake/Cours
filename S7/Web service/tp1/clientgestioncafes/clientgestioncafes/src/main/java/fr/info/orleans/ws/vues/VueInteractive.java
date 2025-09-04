package fr.info.orleans.ws.vues;

import fr.info.orleans.ws.controleur.Controleur;

public interface VueInteractive {

    void setControleur(Controleur controleur);
    Controleur getControleur();

}
