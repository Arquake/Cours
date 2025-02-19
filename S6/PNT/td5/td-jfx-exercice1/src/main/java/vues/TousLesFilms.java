package vues;

import controleur.Controleur;
import facadeCreaFilm.FacadeScreen;
import facadeCreaFilm.FacadeScreenImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TousLesFilms implements VueInteractive {

    private static Controleur CONTROLLER;
    private static FacadeScreen FACADESCREEN;
    public TextArea filmList;
    private Stage stage;
    private Scene scene;

    public void setFACADESCREEN(FacadeScreen FACADESCREEN) {
        TousLesFilms.FACADESCREEN = FACADESCREEN;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void setControleur(Controleur controleur) {
        CONTROLLER = controleur;
    }

    public void show() {
        stage.setScene(scene);
        filmList.setEditable(false);
        StringBuilder text = new StringBuilder();
        for (var item: FACADESCREEN.getAllFilms()) {
            text.append(item.getTitre()).append(", ").append(item.getRealisateur()).append("\n");
        }
        filmList.setText(text.toString());
        stage.show();
    }

    public static TousLesFilms creerVue(Stage stage, Controleur controleur, FacadeScreen facade) {
        URL location = TousLesFilms.class.getResource("/vues/TousLesFilms.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            BorderPane borderPane = fxmlLoader.load();

            TousLesFilms vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setControleur(controleur);
            vue.setFACADESCREEN(facade);
            vue.setScene(new Scene(borderPane, 600, 700));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("Problème fxml : TousLesFilms.fxml");
        }
    }

    public void retourMenu(ActionEvent event) {
        CONTROLLER.gotoMenu();
    }
}
