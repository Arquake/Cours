package pnt;

import controleur.abstractControleur.AbstractControleur;
import controleur.ControleurImpl;
import controleur.ordres.EcouteurOrdre;
import javafx.application.Application;
import javafx.stage.Stage;
import modele.FacadeModele;
import modele.FacadeModeleImpl;
import vues.GestionnaireVue;
import vues.GestionnaireVueImpl;

import java.io.IOException;

public class AppFXML extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GestionnaireVue gestionnaireVue = new GestionnaireVueImpl(stage);
        FacadeModele facadeModele = new FacadeModeleImpl();
        ControleurImpl controle = new ControleurImpl(gestionnaireVue, facadeModele,
                (controleur, gestionnaireVue1) -> {
            // Propagation du contrôleur pour toutes les vues
            gestionnaireVue1.getVuesInteractives().stream().forEach(vueInteractive ->
                    vueInteractive.setControleur(controleur));
            // Inscription des ecouteurs d'ordres
            gestionnaireVue1.getEcouteurOrdres().stream().forEach(ecouteurOrdre ->
                    ecouteurOrdre.setAbonnement(controleur));
        });
        controle.run();
    }


    public static void main(String[] args) {
        launch();
    }
}