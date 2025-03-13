package vues;

import controleur.ControleurImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.GenreFilm;
import ordres.EcouteurOrdre;
import ordres.LanceurOrdre;
import ordres.TypeOrdre;
import vues.abstractvue.AbstractVueInteractive;
import vues.abstractvue.Vue;

import java.io.IOException;
import java.util.ArrayList;

public class Rechercher extends AbstractVueInteractive implements EcouteurOrdre {

    private ControleurImpl controleur;

    @FXML
    VBox mainVbox;

    @FXML
    ComboBox<GenreFilm> genres;

    public static Rechercher creerVue(GestionnaireVueImpl gestionnaire)  {
        FXMLLoader fxmlLoader = new FXMLLoader(Rechercher.class.getResource("rechercher.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        Rechercher vue = fxmlLoader.getController();
        vue.initialisation();
        gestionnaire.ajouterVueInteractive(vue);
        gestionnaire.ajouterEcouteurOrdre(vue);
        return vue;
    }

    public void gotomenu(ActionEvent actionEvent) {
        getControleur().gotoMenu();
    }

    public void rechercherParGenre(ActionEvent event) {
        getControleur().gotoConsulterGenre(genres.getValue());
    }

    @Override
    protected Parent getTopParent() {
        return mainVbox;
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this, TypeOrdre.SHOW_RECHERCHE, TypeOrdre.ERROR_RECHERCHE);
    }

    @Override
    public void traiter(TypeOrdre e) {
        if (e == TypeOrdre.SHOW_RECHERCHE) {
            genres.setItems(FXCollections.observableList(new ArrayList<>(getControleur().getGenres())));
            genres.setValue(GenreFilm.ACTION);
        }
        else if ( e == TypeOrdre.ERROR_RECHERCHE ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Genre inexistant");
            alert.setTitle("Genre inexistant");
            alert.showAndWait();
        }
    }
}
