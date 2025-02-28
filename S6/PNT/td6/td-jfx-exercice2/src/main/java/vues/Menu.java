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

public class Menu extends Vue implements VueInteractive {
   private Controleur controleur;


    public static Menu creerVue(Controleur controleur, Stage stage) {
        Menu menu = new Menu();
        menu.initialiserVue();
        menu.setControleur(controleur);
        menu.setStage(stage);
        return menu;
    }

    private void initialiserVue() {
        BorderPane borderPane = new BorderPane();
        Label label = new Label("Les films");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(42));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        label.setPrefSize(label.getMaxWidth(),label.getMaxHeight());
        VBox vBox = new VBox();

        //BorderPane.setAlignment(label, Pos.CENTER);
        //BorderPane.setAlignment(vBox,Pos.CENTER);
        Button gotoAjouter = new Button("Ajouter");
        gotoAjouter.setFont(Font.font(24));
        gotoAjouter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Button gotoConsulter = new Button("Consulter");
        gotoConsulter.setFont(Font.font(24));
        gotoConsulter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        vBox.getChildren().addAll( gotoConsulter, gotoAjouter);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);

        gotoConsulter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controleur.gotoConsulter();
            }
        });
        gotoAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controleur.gotoAjout();
            }
        });

        borderPane.setCenter(vBox);
        borderPane.setTop(label);
       setScene(new Scene(borderPane, 640, 480));
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }




}
