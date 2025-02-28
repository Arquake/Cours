package vues;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.GenreFilm;

import java.io.IOException;
import java.util.Collection;

public class Ajout extends Vue implements VueInteractive {
    private Controleur controleur;

    public Parent getTop() {
        return mainVbox;
    }
    @FXML
    VBox mainVbox;
    @FXML
    TextField titre;
    @FXML
    ComboBox<GenreFilm> genre;
    @FXML
    TextField realisateur;



    public static Ajout creerVue(Controleur controleur, Stage stage)  {
        FXMLLoader fxmlLoader = new FXMLLoader(Ajout.class.getResource("ajout.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        Ajout vue = fxmlLoader.getController();
        vue.setControleur(controleur);
        vue.setStage(stage);
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


    public void creerFilm(ActionEvent actionEvent) {
        System.out.println(titre.getText());
        System.out.println(genre.getValue());
        controleur.creerFilm(titre.getText(), genre.getValue().toString(), realisateur.getText());
    }

    public void viderChamps() {
        titre.setText("");
        genre.setValue(GenreFilm.ACTION);
        realisateur.setText("");
    }

    public void afficherErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,message);
        alert.setTitle(titre);
        alert.showAndWait();
    }

    public void show() {
        ObservableList<GenreFilm> genres = FXCollections.observableArrayList(GenreFilm.values());
        System.out.println(genres);
        this.genre.setItems(genres);
        this.genre.setValue(genres.get(0));
        super.show();
    }
}
