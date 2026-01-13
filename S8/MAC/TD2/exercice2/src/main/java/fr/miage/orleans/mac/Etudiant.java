package fr.miage.orleans.mac;

public class Etudiant {

    private String numero;
    private String nom;

    public Etudiant(String numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }

    public Etudiant() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void getNotification(Concert concert) {

    }
}
