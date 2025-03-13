package vues;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import modeleCreaFilm.GenreFilm;
import ordres.EcouteurOrdre;
import ordres.LanceurOrdre;
import ordres.TypeOrdre;
import vues.abstractvue.AbstractVueInteractive;

import java.io.IOException;
import java.util.ArrayList;

public class Ajout extends AbstractVueInteractive implements EcouteurOrdre {

    @FXML
    VBox mainVbox;
    @FXML
    TextField titre;
    @FXML
    ComboBox<GenreFilm> genre;
    @FXML
    TextField realisateur;

    public static Ajout creerVue(GestionnaireVueImpl gestionnaire)  {
        FXMLLoader fxmlLoader = new FXMLLoader(Ajout.class.getResource("ajout.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        Ajout vue = fxmlLoader.getController();
        vue.initialisation();
        gestionnaire.ajouterEcouteurOrdre(vue);
        gestionnaire.ajouterVueInteractive(vue);
        return vue;
    }

    public void gotomenu(ActionEvent actionEvent) {
        getControleur().gotoMenu();
    }

    private void chargerGenres(){
        this.genre.setItems(FXCollections.observableList(new ArrayList<>(getControleur().getGenres())));
    }

    public void creerFilm(ActionEvent actionEvent) {
        getControleur().enregistrerFilm(titre.getText(), realisateur.getText(), genre.getSelectionModel().getSelectedItem());
    }

    public void viderChamps() {
        titre.setText("");
        genre.getSelectionModel().clearSelection();
        realisateur.setText("");
    }

    public void afficherMessage(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type, message);
        alert.setTitle(titre);
        alert.showAndWait();
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this, TypeOrdre.GENRE_INCONNU_ERROR, TypeOrdre.NOM_DEJA_EXISTANT_ERROR, TypeOrdre.CHAMP_VIDE_ERROR, TypeOrdre.NOUVEAU_FILM, TypeOrdre.SHOW_AJOUT);
    }

    @Override
    public void traiter(TypeOrdre e) {
        if (e == TypeOrdre.SHOW_AJOUT) {
            chargerGenres();
        }
        if (e == TypeOrdre.CHAMP_VIDE_ERROR) {
            afficherMessage("Un des champs est vide", "Les champs ne doivent pas être vide", Alert.AlertType.ERROR);
        }
        else if (e == TypeOrdre.GENRE_INCONNU_ERROR) {
            afficherMessage("Genre inconnu", "Le genre sélectionné n'existe pas", Alert.AlertType.ERROR);
        }
        else if (e == TypeOrdre.NOM_DEJA_EXISTANT_ERROR) {
            afficherMessage("Un des champs est vide", "Les champs ne doivent pas être vide", Alert.AlertType.ERROR);
        }
        else if (e == TypeOrdre.NOUVEAU_FILM) {
            afficherMessage("Film créé", "Film créé !", Alert.AlertType.CONFIRMATION);
        }
        viderChamps();
    }

    @Override
    protected Parent getTopParent() {
        return mainVbox;
    }
}
