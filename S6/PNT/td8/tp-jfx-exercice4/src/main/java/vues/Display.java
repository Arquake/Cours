package vues;

import controller.Controller;
import facadeLudotheque.CategorieNotFoundException;
import facadeLudotheque.InformationManquanteException;
import facadeLudotheque.JeuDejaExistant;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleLudotheque.CategorieJeu;
import modeleLudotheque.Jeu;

import java.io.IOException;
import java.util.ArrayList;

public class Display extends Vue implements VueInteractive {
    @FXML
    VBox vbox;

    @FXML
    ListView<Jeu> jeux;

    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static Display create(Controller controller, Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Display.class.getResource("display.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Counldn't load menu");
        }

        Display vue = fxmlLoader.getController();
        vue.setController(controller);
        vue.setStage(stage);
        vue.setScene(new Scene(vue.getTop()));
        return vue;
    }

    private Parent getTop() {
        return vbox;
    }

    public void setJeux(CategorieJeu cat) {
        jeux.setItems(FXCollections.observableList(new ArrayList<>(controller.getJeuxFrom(cat))));
        jeux.setCellFactory(param -> new ListCell<Jeu>() {
            @Override
            protected void updateItem(Jeu item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null ) {
                    setText("No body");
                } else {
                    setText(item.getNomJeu() + " : " + item.getResume());
                }
            }
        });
    }

    public void toMenu(ActionEvent event) {
        controller.gotoMenu();
    }
}