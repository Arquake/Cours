package vues;

import controleur.ControleurImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.Film;
import ordres.EcouteurOrdre;
import ordres.LanceurOrdre;
import ordres.TypeOrdre;
import vues.abstractvue.AbstractVueInteractive;
import vues.abstractvue.Vue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class TousLesFilms extends AbstractVueInteractive implements EcouteurOrdre {

    @FXML
    VBox mainVbox;

    @FXML
    ListView<Film> lesFilms;

    Collection<Film> films;

    @FXML
    private void handleItemClick(MouseEvent event) {
        Film selectedItem = lesFilms.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            getControleur().gotoInfoFilm(selectedItem);
        }
    }

    @Override
    public Parent getTopParent() {
        return mainVbox;
    }

    public static TousLesFilms creerVue(GestionnaireVueImpl gestionnaire) {
        FXMLLoader fxmlLoader = new FXMLLoader(TousLesFilms.class.getResource("tousLesFilms.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        TousLesFilms vue = fxmlLoader.getController();
        vue.initialisation();
        vue.lesFilms.setOnMouseClicked(vue::handleItemClick);
        gestionnaire.ajouterVueInteractive(vue);
        gestionnaire.ajouterEcouteurOrdre(vue);
        return vue;

    }
    public void gotomenu(ActionEvent actionEvent) {
        getControleur().gotoMenu();
    }



    @Override
    public void show() {
        films = getControleur().getFilms();
        showFilms();
    }

    private void showFilms() {
        this.lesFilms.setCellFactory(param -> new ListCell<Film>() {
            @Override
            protected void updateItem(Film item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null ) {
                    setText("No body");
                } else {
                    setText(item.getTitre() + " ("+item.getRealisateur()+", "+item.getGenre()+")");
                }
            }
        });
    }


    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this, TypeOrdre.NOUVEAU_FILM, TypeOrdre.SHOW_FILMS, TypeOrdre.SHOW_FILMS_RECHERCHE);
    }

    @Override
    public void traiter(TypeOrdre e) {
        if (e == TypeOrdre.NOUVEAU_FILM || e == TypeOrdre.SHOW_FILMS) {
            Collection<Film> films = getControleur().getFilms();
            this.lesFilms.setItems(FXCollections.observableList(new ArrayList<>(films)));
            showFilms();
        }
        else if (e == TypeOrdre.SHOW_FILMS_RECHERCHE) {
            Collection<Film> films = getControleur().getFilmsParGenreRecherche();
            this.lesFilms.setItems(FXCollections.observableList(new ArrayList<>(films)));
            showFilms();
        }
    }
}
