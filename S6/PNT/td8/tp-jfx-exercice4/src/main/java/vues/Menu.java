package vues;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import java.io.IOException;

public class Menu extends Vue implements VueInteractive {

    @FXML
    TextField titre;

    @FXML
    ComboBox<String> categories;

    @FXML
    TextField description;

    @FXML
    VBox vbox;

    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static Menu create(Controller controller, Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("vues/menu.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Counldn't load menu");
        }

        Menu vue = fxmlLoader.getController();
        vue.setController(controller);
        vue.setStage(stage);
        vue.setScene(new Scene(vue.getTop()));
        return vue;
    }

    private Parent getTop() {
        return vbox;
    }


}
