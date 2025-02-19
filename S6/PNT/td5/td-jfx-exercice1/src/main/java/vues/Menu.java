package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu implements VueInteractive {

    private Stage stage;
    private Scene scene;

    private BorderPane borderPane;
    private Button consulterButton;
    private Button ajouterButton;

    private Controleur CONTROLLER;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void setControleur(Controleur controleur) {
        CONTROLLER = controleur;
    }

    public void show() {
        stage.setScene(scene);
        stage.show();
    }

    public static Menu creerVue(Stage stage, Controleur controleur) {
        Menu menu = new Menu();
        menu.setStage(stage);
        menu.setControleur(controleur);
        menu.initialiserComposant();
        return menu;
    }

    private void initialiserComposant() {
        this.borderPane = new BorderPane();
        this.consulterButton = new Button("Consulter");
        this.ajouterButton = new Button("Ajouter");

        ajouterButton.setOnAction(e -> {CONTROLLER.gotoAjout();});
        consulterButton.setOnAction(e -> {CONTROLLER.gotoConsulter();});

        this.ajouterButton.setMaxWidth(Double.MAX_VALUE);
        this.consulterButton.setMaxWidth(Double.MAX_VALUE);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(20);
        vb.getChildren().addAll(consulterButton, ajouterButton);
        this.borderPane.setCenter(vb);

        Label label = new Label("Les Films");
        label.setFont(Font.font(32));
        this.borderPane.setTop(label);

        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(vb, Pos.CENTER);

        this.setScene(new Scene(this.borderPane, 600, 700));
    }
}
