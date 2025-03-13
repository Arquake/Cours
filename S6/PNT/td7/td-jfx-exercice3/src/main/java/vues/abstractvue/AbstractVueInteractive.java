package vues.abstractvue;

import controleur.ControleurImpl;
import javafx.scene.Scene;
import vues.VueInteractive;

public abstract class AbstractVueInteractive extends Vue implements VueInteractive {
    private ControleurImpl controleur;


    @Override
    public ControleurImpl getControleur() {
        return controleur;
    }

    @Override
    public void setControleur(ControleurImpl controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialisation() {
        super.setScene(new Scene(this.getTopParent()));
    }
}
