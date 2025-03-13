package vues;

import controleur.ControleurImpl;
import controleur.ordres.EcouteurOrdre;
import javafx.stage.Stage;
import vues.abstractVues.AbstractVueInteractive;

import java.util.Collection;

public interface GestionnaireVue extends EcouteurOrdre {
    Stage getStage();

    void ajouterEcouteurOrdre(EcouteurOrdre ecouteurOrdre);

    Collection<EcouteurOrdre> getEcouteurOrdres();

    void ajouterVueInteractive(VueInteractive vueInteractive);

    Collection<VueInteractive> getVuesInteractives();
}
