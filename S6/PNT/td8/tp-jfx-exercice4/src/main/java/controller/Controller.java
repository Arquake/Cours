package controller;

import facadeLudotheque.CategorieNotFoundException;
import facadeLudotheque.FacadeLudotheque;
import facadeLudotheque.InformationManquanteException;
import facadeLudotheque.JeuDejaExistant;
import javafx.stage.Stage;
import modeleLudotheque.CategorieJeu;
import modeleLudotheque.Jeu;
import vues.Display;
import vues.Menu;

import java.util.Collection;
import java.util.List;

public class Controller {

    FacadeLudotheque facadeScreen;
    Menu menu;

    Display display;

    public Controller(FacadeLudotheque facadeScreen, Stage stage) {
        this.facadeScreen = facadeScreen;
        menu = Menu.create(this, stage);
        display = Display.create(this, stage);
    }

    public void run() {
        showMenu();
    }

    private void showMenu() {
        menu.show();
    }

    private void showDisplay() {
        display.show();
    }

    public void gotoDisplay(CategorieJeu cat) {
        showDisplay();
        display.setJeux(cat);
    }

    public void gotoMenu() {
        showMenu();
    }

    public Collection<String> getGenres() {
        return this.facadeScreen.getAlls();
    }

    public void createGame(String nom, String cat, String desc) throws CategorieNotFoundException, InformationManquanteException, JeuDejaExistant {
        this.facadeScreen.ajoutJeu(nom, cat, desc);
    }

    public Collection<Jeu> getJeuxFrom(CategorieJeu cat) {
        try {
            return facadeScreen.getJeuxDuneCategorie(cat.toString());
        } catch (CategorieNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
