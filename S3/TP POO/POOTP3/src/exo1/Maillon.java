package exo1;

public class Maillon {
    private Object valeur = new Object();
    private Maillon suivant;

    public Maillon(Object valeur, Maillon suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }

    public Object getValeur() {
        return valeur;
    }

    public Maillon getSuivant() {
        return suivant;
    }
}

