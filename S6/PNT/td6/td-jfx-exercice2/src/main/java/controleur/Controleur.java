package controleur;

import facadeCreaFilm.FacadeScreen;
import facadeCreaFilm.GenreNotFoundException;
import facadeCreaFilm.NomFilmDejaExistantException;
import javafx.stage.Stage;
import modeleCreaFilm.Film;
import vues.Ajout;
import vues.Menu;
import vues.TousLesFilms;

import java.util.Collection;

public class Controleur {

    private Menu menu;
    private TousLesFilms tousLesFilms;
    private Ajout ajout;

    private FacadeScreen facadeScreen;

    public Controleur(FacadeScreen facadeScreen, Stage stage) {
        this.facadeScreen = facadeScreen;
        menu = Menu.creerVue(this,stage);
        tousLesFilms = TousLesFilms.creerVue(this,stage);
        ajout = Ajout.creerVue(this,stage);

    }

    private void showMenu() {
      menu.show();
    }
    public void showFilms(){
       tousLesFilms.show();
    }
    public void showAjout(){
       ajout.show();
    }


    public void run() {
        showMenu();
    }


    public void gotoConsulter() {

        showFilms();
    }

    public void gotoMenu() {
        showMenu();
    }

    public void creerFilm(String titre, String genre, String realisateur)  {
        try {
            facadeScreen.creerFilm(titre, realisateur,genre);
            ajout.viderChamps();
            showMenu();

        } catch (GenreNotFoundException e) {
           ajout.afficherErreur("Erreur de genre","Genre inexistant !");
           ajout.viderChamps();
           showAjout();
        }
        catch ( NomFilmDejaExistantException e) {
            ajout.afficherErreur("Erreur de film","Le titre du film existe déjà !");
            ajout.viderChamps();
            showAjout();
        }
}

    public void gotoAjout() {
        showAjout();
    }

    public Collection<Film> getFilms() {
        return facadeScreen.getAllFilms();
    }
}
