package fr.info.orleans.ws;

import fr.info.orleans.ws.controleur.Controleur;
import fr.info.orleans.ws.vues.GestionnaireVue;
import fr.info.orleans.ws.vues.GestionnaireVueImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        GestionnaireVue gestionnaireVue = new GestionnaireVueImpl(stage);
        Controleur controleur = new Controleur(gestionnaireVue);
        controleur.run();

    }


    public static void main(String[] args) {
        launch();
    }

}