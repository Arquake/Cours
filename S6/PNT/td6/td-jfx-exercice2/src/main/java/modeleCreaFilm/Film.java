package modeleCreaFilm;

public class Film {

    private int id;
    private String titre;
    private String realisateur;
    private GenreFilm genre;



    public static int ID =0;

    public Film(String titre, String realisateur,GenreFilm genre) {
        this.titre=titre;
        this.realisateur=realisateur;
        this.genre=genre;
        this.id = ID;
        ID++;
    }

    public String getTitre() {
        return titre;
    }

    public GenreFilm getGenre() {
        return genre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public int getId() {
        return id;
    }



}
