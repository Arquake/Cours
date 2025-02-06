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

public class Menu implements VueInteractive {

    Controleur CONTROLLER;

    @Override
    public void setControleur(Controleur controleur) {
        CONTROLLER = controleur;
    }

    public void show() {
    }
}
