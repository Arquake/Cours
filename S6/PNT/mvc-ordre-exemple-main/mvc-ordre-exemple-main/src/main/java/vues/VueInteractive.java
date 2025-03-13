package vues;

import controleur.ControleurImpl;
import vues.Vue;

public interface VueInteractive extends Vue {
    ControleurImpl getControleur();

    void setControleur(ControleurImpl controleur);
}
