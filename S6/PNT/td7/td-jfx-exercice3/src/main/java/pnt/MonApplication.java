package pnt;

import controleur.ControleurImpl;
import facadeCreaFilm.FacadeScreenImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import vues.GestionnaireVueImpl;

import java.io.IOException;

public class MonApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        GestionnaireVueImpl gestionnaire = new GestionnaireVueImpl(stage);
        ControleurImpl controleur = new ControleurImpl(gestionnaire, new FacadeScreenImpl(),
            (controller, gestionnaireVue1)-> {
                gestionnaireVue1.getVuesInteractives().stream().forEach(vueInteractive -> vueInteractive.setControleur(controller));
                gestionnaireVue1.getEcouteurOrdres().stream().forEach(ecouteurOrdre -> ecouteurOrdre.setAbonnement(controller));
        });
        controleur.run();
    }

    public static void main(String[] args) {
        launch();
    }
}
