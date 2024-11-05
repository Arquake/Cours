package tp4.lambda;

public class Personne {
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int prenomLength() {
        return prenom.length();
    }

    public char firstLetterName() {
        return nom.charAt(0);
    }
}
