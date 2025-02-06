package facadeCreaFilm;

import modeleCreaFilm.Film;
import modeleCreaFilm.GenreFilm;

import java.util.Collection;

public interface FacadeScreen {

    /**
     * Permet de récupérer tous les genres de films
     * @return
     */
    Collection<GenreFilm> getAllGenres();

    /**
     * Permet de récupérer tous les films d'un genre donné
     * @param genre : nom du genre visé
     * @return tous les films du genre visé
     * @throws GenreNotFoundException
     */
    Collection<Film> getFilmsDuGenre(String genre) throws GenreNotFoundException;

    /**
     * Permet de récupérer le film à partir de son id
     * @param id
     * @return
     */
    Film getLeFilm(int id);

    /**
     * Permet de créer un film
     * @param titre
     * @param realisateur
     * @param genreFilm
     * @return
     */

    void  creerFilm(String titre, String realisateur,String genreFilm) throws GenreNotFoundException, NomFilmDejaExistantException;

    /**
     * Retourne la liste de tous les films
     * @return
     */
    Collection<Film> getAllFilms();



}
