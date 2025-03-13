package controleur;

import facadeCreaFilm.FacadeScreen;
import facadeCreaFilm.GenreNotFoundException;
import facadeCreaFilm.NomFilmDejaExistantException;
import modeleCreaFilm.Film;
import modeleCreaFilm.GenreFilm;
import ordres.TypeOrdre;
import vues.*;

import java.util.*;

public class ControleurImpl extends AbstractControleur {

    private FacadeScreen facadeModele;

    public ControleurImpl(GestionnaireVue gestionnaireVue, FacadeScreen facadeModele, ControleurSetUp controleurSetUp) {
        super(gestionnaireVue);
        this.facadeModele = facadeModele;
        controleurSetUp.setUp(this, getGestionnaireVue());
    }

    @Override
    public void run() {
        this.fireOrdre(TypeOrdre.LOAD_FILMS);
        this.fireOrdre(TypeOrdre.SHOW_MENU);
    }

    private Film filmSelectionne;

    private GenreFilm genreRecherche;

    public void gotoInfoFilm(Film film) {
        this.filmSelectionne = this.facadeModele.getLeFilm(film.getId());
        this.fireOrdre(TypeOrdre.SHOW_FILM);
    }

    public Film getFilmSelectionne() {
        return filmSelectionne;
    }

    public void gotoMenu() {
        this.fireOrdre(TypeOrdre.SHOW_MENU);
    }

    public Collection<Film> getFilms() {
        return this.facadeModele.getAllFilms();
    }

    public Collection<GenreFilm> getGenres() {
        return this.facadeModele.getAllGenres();
    }

    public void enregistrerFilm(String titre, String realisateur, GenreFilm genre) {
        if (titre.isEmpty() || realisateur.isEmpty()) {
            this.fireOrdre(TypeOrdre.CHAMP_VIDE_ERROR);
            return;
        }
        else if (genre == null) {
            this.fireOrdre(TypeOrdre.GENRE_INCONNU_ERROR);
            return;
        }
        try {
            this.facadeModele.creerFilm(titre, realisateur, genre);
            this.fireOrdre(TypeOrdre.NOUVEAU_FILM);
            this.fireOrdre(TypeOrdre.SHOW_MENU);
        } catch (GenreNotFoundException e) {
            this.fireOrdre(TypeOrdre.GENRE_INCONNU_ERROR);
        } catch (NomFilmDejaExistantException e) {
            this.fireOrdre(TypeOrdre.NOM_DEJA_EXISTANT_ERROR);
        }
    }

    public void gotoConsulterGenre(GenreFilm genre) {

        if ( genre != null) {
            genreRecherche = genre;
            fireOrdre(TypeOrdre.SHOW_FILMS_RECHERCHE);
        }
        else {
            fireOrdre(TypeOrdre.ERROR_RECHERCHE);
        }
    }

    public Collection<Film> getFilmsParGenreRecherche() {
        try {
            return facadeModele.getFilmsDuGenre(genreRecherche.toString());
        } catch (GenreNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
