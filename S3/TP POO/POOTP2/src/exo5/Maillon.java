package exo5;

public class Maillon {
    private String valeur;
    private Maillon suivant;

    public Maillon(String valeur, Maillon suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }

    public String getValeur() {
        return valeur;
    }

    public Maillon getSuivant() {
        return suivant;
    }
}

