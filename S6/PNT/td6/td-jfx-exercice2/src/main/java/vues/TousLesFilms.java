package vues;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.Film;
import modeleCreaFilm.GenreFilm;

import java.io.IOException;
import java.util.Collection;

public class TousLesFilms extends Vue implements VueInteractive {
    private Controleur controleur;
    public Parent getTop() {
        return mainVbox;
    }

    @FXML
    VBox mainVbox;

    @FXML
    ListView<Film> lesFilms;



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
        return vue;

    }

    public void gotomenu(ActionEvent actionEvent) {
        controleur.gotoMenu();
    }


    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }


    public void show(){
        ObservableList<Film> films = FXCollections.observableArrayList(this.controleur.getFilms());
        this.lesFilms.setItems(films);
        super.show();
    }

}
