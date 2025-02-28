package pnt;

import controleur.Controleur;
import facadeCreaFilm.FacadeScreenImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MonApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Controleur controleur = new Controleur(new FacadeScreenImpl(),stage);
        controleur.run();
    }

    public static void main(String[] args) {
        launch();
    }
}
