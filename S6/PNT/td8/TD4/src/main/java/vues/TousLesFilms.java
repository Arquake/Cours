package vues;

import controleur.Controleur;
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
import modeleCreaFilm.GenreFilm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class TousLesFilms extends Vue implements VueInteractive {
    private Controleur controleur;

    @FXML
    VBox mainVbox;

    @FXML
    ListView<Film> lesFilms;

    @FXML
    private void handleItemClick(MouseEvent event) {
        Film selectedItem = lesFilms.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            controleur.gotoInfo(selectedItem);
        }
    }

    public Parent getTop() {
        return mainVbox;
    }

    public static TousLesFilms creerVue(Controleur controleur, Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(TousLesFilms.class.getResource("tousLesFilms.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }
        TousLesFilms vue = fxmlLoader.getController();

        vue.setControleur(controleur);
        vue.setStage(stage);
        vue.setScene(new Scene(vue.getTop()));
        vue.lesFilms.setOnMouseClicked(vue::handleItemClick);
        return vue;

    }
    public void gotomenu(ActionEvent actionEvent) {
        controleur.gotoMenu();
    }


    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }



    @Override
    public void show() {
        Collection<Film> films = controleur.getLesFilms();
        showFilms(films);
    }

    public void show(GenreFilm genre) {
        Collection<Film> films = controleur.getFilmsDuGenre(genre);
        showFilms(films);
    }

    private void showFilms(Collection<Film> films) {
        this.lesFilms.setItems(FXCollections.observableList(new ArrayList<>(films)));
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
        super.show();
    }
}
