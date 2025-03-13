package vues;

import controleur.ControleurImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ordres.EcouteurOrdre;
import ordres.LanceurOrdre;
import ordres.TypeOrdre;
import vues.abstractvue.AbstractVueInteractive;
import vues.abstractvue.Vue;

public class Menu extends AbstractVueInteractive implements EcouteurOrdre {

    private BorderPane parent;

    public static Menu creerVue(GestionnaireVueImpl gestionnaire) {
        Menu vue = new Menu();
        vue.initialiserVue();
        gestionnaire.ajouterVueInteractive(vue);
        gestionnaire.ajouterEcouteurOrdre(vue);
        return vue;
    }

    private void initialiserVue() {
        parent = new BorderPane();
        Label label = new Label("Les films");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(42));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        label.setPrefSize(label.getMaxWidth(),label.getMaxHeight());
        VBox mainbox = new VBox();

        //BorderPane.setAlignment(label, Pos.CENTER);
        //BorderPane.setAlignment(vBox,Pos.CENTER);
        Button gotoAjouter = new Button("Ajouter");
        gotoAjouter.setFont(Font.font(24));
        gotoAjouter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Button gotoConsulter = new Button("Consulter");
        gotoConsulter.setFont(Font.font(24));
        gotoConsulter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Button gotoRechercher = new Button("Rechercher les films par genre");
        gotoRechercher.setFont(Font.font(24));
        gotoRechercher.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        mainbox.getChildren().addAll( gotoConsulter, gotoAjouter, gotoRechercher);
        mainbox.setAlignment(Pos.CENTER);
        mainbox.setSpacing(40);

        gotoConsulter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getControleur().fireOrdre(TypeOrdre.SHOW_FILMS);
            }
        });
        gotoAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getControleur().fireOrdre(TypeOrdre.SHOW_AJOUT);
            }
        });
        gotoRechercher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getControleur().fireOrdre(TypeOrdre.SHOW_RECHERCHE);
            }
        });

        parent.setCenter(mainbox);
        parent.setTop(label);

        this.setScene(new Scene(parent, 600, 400));
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this, TypeOrdre.SHOW_MENU);
    }

    @Override
    public void traiter(TypeOrdre e) {}

    @Override
    protected Parent getTopParent() {
        return parent;
    }
}
