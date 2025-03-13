package vues;

import controleur.ControleurImpl;

public interface VueInteractive {
        ControleurImpl getControleur();

        void setControleur(ControleurImpl controleur);
}
