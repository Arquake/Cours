package modeleLudotheque;

public class Jeu {

    private int id;
    private String nomJeu;
    private CategorieJeu categorieJeu;
    private String resume;


    public static int ID =0;

    public Jeu(String nomJeu, CategorieJeu categorieJeu, String resume) {
        this.nomJeu = nomJeu;
        this.categorieJeu = categorieJeu;
        this.resume = resume;
        this.id = ID;
        ID++;
    }

    public String getNomJeu() {
        return nomJeu;
    }

    public CategorieJeu getCategorieJeu() {
        return categorieJeu;
    }

    public String getResume() {
        return resume;
    }

    public int getId() {
        return id;
    }
}
