package controller;

import facadeLudotheque.FacadeLudotheque;
import javafx.stage.Stage;
import vues.Menu;

public class Controller {

    FacadeLudotheque facadeScreen;
    Menu menu;

    public Controller(FacadeLudotheque facadeScreen, Stage stage) {
        this.facadeScreen = facadeScreen;
        menu = Menu.create(this, stage);
    }

    public void run() {
        showMenu();
    }

    private void showMenu() {

    }
}
