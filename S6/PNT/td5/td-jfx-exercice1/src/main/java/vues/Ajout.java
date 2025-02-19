package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modeleCreaFilm.Film;

import java.io.IOException;
import java.net.URL;

public class Ajout implements VueInteractive {

    private Stage stage;
    private Scene scene;

    private Controleur CONTROLLER;

    @FXML
    private TextField titre;
    @FXML
    private TextField genre;
    @FXML
    private TextField realisateur;

    public void setStage(Stage stage) {this.stage = stage;}

    public void setScene(Scene scene) {this.scene = scene;}

    @Override
    public void setControleur(Controleur controleur) {CONTROLLER = controleur;}

    public void show() {
        stage.setScene(scene);
        stage.show();
    }

    public static Ajout creerVue(Stage stage, Controleur controleur) {
        URL location = Ajout.class.getResource("/vues/Ajout.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            BorderPane borderPane = fxmlLoader.load();

            Ajout vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setControleur(controleur);
            vue.setScene(new Scene(borderPane, 600, 700));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("Problème fxml : Ajout.fxml");
        }
    }


    public void afficherErreur(String typeErreur, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle(typeErreur);
        alert.showAndWait();
    }

    public void viderChamps() {
        titre.setText("");
        genre.setText("");
        realisateur.setText("");
    }

    public void ajouterFilm(ActionEvent event) {
        CONTROLLER.creerFilm(titre.getText(), genre.getText(), realisateur.getText());
    }
}
