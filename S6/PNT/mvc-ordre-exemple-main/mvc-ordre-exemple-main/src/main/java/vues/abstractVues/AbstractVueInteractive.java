package vues.abstractVues;

import controleur.ControleurImpl;
import vues.VueInteractive;

public abstract class AbstractVueInteractive extends AbstractVue implements VueInteractive {

    private ControleurImpl controleur;


    @Override
    public ControleurImpl getControleur() {
        return controleur;
    }

    @Override
    public void setControleur(ControleurImpl controleur) {
        this.controleur = controleur;
    }
}
