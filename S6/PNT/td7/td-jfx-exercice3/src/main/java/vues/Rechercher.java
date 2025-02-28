package vues;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.GenreFilm;

import java.io.IOException;
import java.util.ArrayList;

public class Rechercher extends Vue implements VueInteractive{

    private Controleur controleur;

    @FXML
    VBox mainVbox;

    @FXML
    ComboBox<GenreFilm> genres;

    public Parent getTop() {
        return mainVbox;
    }

    public static Rechercher creerVue(Controleur controleur, Stage stage)  {
        FXMLLoader fxmlLoader = new FXMLLoader(Rechercher.class.getResource("rechercher.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        Rechercher vue = fxmlLoader.getController();
        vue.setControleur(controleur);
        vue.setStage(stage);
        vue.genres.setItems(FXCollections.observableList(new ArrayList<>(controleur.getGenres())));
        vue.genres.setValue(GenreFilm.ACTION);
        vue.setScene(new Scene(vue.getTop()));
        return vue;
    }

    public void gotomenu(ActionEvent actionEvent) {
        controleur.gotoMenu();
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void rechercherParGenre(ActionEvent event) {
        controleur.gotoConsulter(genres.getValue());
    }

}
