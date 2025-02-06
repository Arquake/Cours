package facadeCreaFilm;


import modeleCreaFilm.Film;
import modeleCreaFilm.GenreFilm;

import java.util.*;

public class FacadeScreenImpl implements FacadeScreen {

    private Map<GenreFilm, Collection<Film>> lesFilmsParGenre;
    private Collection<Film> tous;


    public FacadeScreenImpl() {
        this.lesFilmsParGenre = new HashMap<>();
        for (GenreFilm genreFilm : GenreFilm.values()) {
            this.lesFilmsParGenre.put(genreFilm, new ArrayList<>());
        }
        this.initialisation();
    }


    private void initialisation() {

        tous = new ArrayList<>();
        tous.add(new Film("Le chevalier noir", "Christopher Nolan,", GenreFilm.ACTION));
        tous.add(new Film("La mission", "Paul Greengrass", GenreFilm.AVENTURE));
        tous.add(new Film("Hors normes", "Eric Toledano, Olivier Nackage", GenreFilm.COMEDIE));
        tous.add(new Film("Les  visiteurs", "Jean-Marie Poiré", GenreFilm.COMEDIE));
        tous.add(new Film("Parasite", "Bong Joon Ho", GenreFilm.THRILLER));
        tous.add(new Film("Le chant du loup", "Antoine Baudry", GenreFilm.THRILLER));
        tous.add(new Film("Harry Potter à l'école des sorciers", "Chris Columbus", GenreFilm.AVENTURE));


        for (Film film : tous) {
            GenreFilm genre = film.getGenre();
            lesFilmsParGenre.get(genre).add(film);


        }
    }

    @Override
    public Collection<GenreFilm> getAllGenres() {
        return this.lesFilmsParGenre.keySet();
    }

    public Collection<Film> getAllFilms() {
        return tous;
    }


    @Override
    public Collection<Film> getFilmsDuGenre(String genre) throws GenreNotFoundException {

        GenreFilm genreFilm = GenreFilm.valueOf(genre);
        if (!Objects.isNull(genreFilm) && this.lesFilmsParGenre.containsKey(genreFilm)) {
            return this.lesFilmsParGenre.get(genreFilm);
        }
        throw new GenreNotFoundException();


    }

    @Override
    public Film getLeFilm(int id) {
        for (Film f : tous) {
            if (f.getId() == id) {
                return f;
            }
        }

        return null;
    }

    @Override
    public void creerFilm(String titre, String realisateur, String genre) throws GenreNotFoundException, NomFilmDejaExistantException {
        for (Film f : tous) {
            if (f.getTitre().equalsIgnoreCase(titre)) {
                throw new NomFilmDejaExistantException();
            }
        }
        try {
            GenreFilm genreFilm = GenreFilm.valueOf(genre);
            Film film = new Film(titre, realisateur, genreFilm);
            tous.add(film);
            lesFilmsParGenre.get(genreFilm).add(film);
        } catch (IllegalArgumentException e) {
            throw new GenreNotFoundException();
        }

    }


}
