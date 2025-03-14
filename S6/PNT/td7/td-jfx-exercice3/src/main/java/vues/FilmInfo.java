package vues;

import controleur.ControleurImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.Arrays;

public class FilmInfo extends AbstractVueInteractive implements EcouteurOrdre {

    @FXML
    VBox mainVbox;

    @FXML
    TableView<Film> table;

    @FXML
    private TableColumn<Film, String> titre;

    @FXML
    private TableColumn<Film,String> realisateur;

    Film film;

    public static FilmInfo creerVue(GestionnaireVueImpl gestionnaire) {
        FXMLLoader fxmlLoader = new FXMLLoader(FilmInfo.class.getResource("filminfo.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        FilmInfo vue = fxmlLoader.getController();

        vue.initialisation();
        vue.initialisationTableView();
        gestionnaire.ajouterVueInteractive(vue);
        gestionnaire.ajouterEcouteurOrdre(vue);
        return vue;
    }

    public void show() {
        super.show();
    }

    public void initialisationTableView(){
        titre.setCellValueFactory(cellData -> {
            Film film = cellData.getValue();
            return new SimpleStringProperty(film != null ? film.getTitre() : "");
        });

        realisateur.setCellValueFactory(cellData -> {
            Film film = cellData.getValue();
            return new SimpleStringProperty(film != null ? film.getRealisateur() : "");
        });
    }

    @Override
    protected Parent getTopParent() {
        return mainVbox;
    }

    public void gotomenu(ActionEvent actionEvent) {
        getControleur().gotoMenu();
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this, TypeOrdre.SHOW_FILM);
    }

    @Override
    public void traiter(TypeOrdre e) {
        if (e == TypeOrdre.SHOW_FILM) {
            film = getControleur().getFilmSelectionne();
            table.getItems().clear();
            table.getItems().add(film);
        }
    }
}
