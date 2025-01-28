package modele;

import java.util.Optional;

public class Pari {
    private long idPari;
    private Utilisateur parieur;    // le parieur
    private Match match;            // le match sur lequel porte le pari
    private String vainqueur;       // le vainqueur prévu ou nul pour match nul
    private Double montant;         // montant du pari
    private Optional<Double> gain;  // montant gagné, calculé à la fin du match

    private static long lastId = 0L;

    public Pari(Utilisateur parieur, Match match, String vainqueur, Double montant) {
        this.idPari = ++lastId;
        this.parieur = parieur;
        this.match = match;
        this.vainqueur = vainqueur;
        this.montant = montant;
        gain = Optional.empty();
    }

    public long getIdPari() {
        return idPari;
    }

    public Utilisateur getParieur() {
        return parieur;
    }

    public Match getMatch() {
        return match;
    }

    public String getVainqueur() {
        return vainqueur;
    }

    public Double getMontant() {
        return montant;
    }

    public Optional<Double> getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = Optional.of(gain);
    }

    public static long getLastId() {
        return lastId;
    }

    @Override
    public String toString() {
        return "Pari{" +
                "idPari=" + idPari +
                ", parieur=" + parieur +
                ", match=" + match +
                ", vainqueur='" + vainqueur + '\'' +
                ", montant=" + montant +
                ", gain=" + gain +
                '}';
    }
}
