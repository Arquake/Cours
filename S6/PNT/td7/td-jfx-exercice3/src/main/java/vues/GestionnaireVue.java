package vues;

import javafx.stage.Stage;
import ordres.EcouteurOrdre;

import java.util.Collection;

public interface GestionnaireVue extends EcouteurOrdre {
    Stage getStage();

    void ajouterEcouteurOrdre(EcouteurOrdre ecouteurOrdre);

    Collection<EcouteurOrdre> getEcouteurOrdres();

    void ajouterVueInteractive(VueInteractive vueInteractive);

    Collection<VueInteractive> getVuesInteractives();
}
