package vues;

import controller.Controller;
import facadeLudotheque.CategorieNotFoundException;
import facadeLudotheque.FacadeLudothequeImpl;
import facadeLudotheque.InformationManquanteException;
import facadeLudotheque.JeuDejaExistant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import modeleLudotheque.CategorieJeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Vue implements VueInteractive {

    @FXML
    TextField titre;

    @FXML
    ComboBox<String> categories;

    @FXML
    TextArea description;

    @FXML
    VBox vbox;

    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static Menu create(Controller controller, Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("menu.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Counldn't load menu");
        }

        Menu vue = fxmlLoader.getController();
        vue.setController(controller);
        vue.setCategories();
        vue.setStage(stage);
        vue.setScene(new Scene(vue.getTop()));
        return vue;
    }

    private Parent getTop() {
        return vbox;
    }

    public void setCategories() {
        categories.setItems(FXCollections.observableList(new ArrayList<>(controller.getGenres())));
        categories.setValue(CategorieJeu.CARTES.toString());
    }

    public void handleSubmit(ActionEvent event) {
        try {
            this.controller.createGame(titre.getText(), categories.getValue(), description.getText());
            controller.gotoDisplay(CategorieJeu.valueOf(categories.getValue()));
        } catch (CategorieNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mauvaises informations");
            alert.setContentText("Catégorie inconnue");
            alert.showAndWait();
        }
        catch (JeuDejaExistant e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mauvaises informations");
            alert.setContentText("Jeu déjà existant");
            alert.showAndWait();
        }
        catch (InformationManquanteException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mauvaises informations");
            alert.setContentText("Cetaines informations sont manquantes");
            alert.showAndWait();
        }
        finally {
            titre.setText("");
            description.setText("");
            categories.setValue(CategorieJeu.CARTES.toString());
        }
    }
}
