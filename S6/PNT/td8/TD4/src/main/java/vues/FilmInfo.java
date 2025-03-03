package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.Film;

import java.io.IOException;

public class FilmInfo extends Vue implements VueInteractive {

    private Controleur controleur;

    @FXML
    VBox mainVbox;

    @FXML
    Label title;

    @FXML
    Label realisateur;

    public Parent getTop() {
        return mainVbox;
    }

    public static FilmInfo creerVue(Controleur controleur, Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(FilmInfo.class.getResource("filminfo.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        FilmInfo vue = fxmlLoader.getController();
        vue.setControleur(controleur);
        vue.setStage(stage);
        vue.setScene(new Scene(vue.getTop()));
        return vue;
    }

    public void show(Film film) {
        title.setText(film.getTitre());
        realisateur.setText(film.getRealisateur());

        super.show();
    }

    public void gotomenu(ActionEvent actionEvent) {
        controleur.gotoMenu();
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}
