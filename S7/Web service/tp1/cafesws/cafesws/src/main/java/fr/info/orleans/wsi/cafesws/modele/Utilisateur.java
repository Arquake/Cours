package fr.info.orleans.wsi.cafesws.modele;

public class Utilisateur {
    private String nom;
    private String prenom;
    private String email;
    private String idAleatoire;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setIdAleatoire(String idAleatoire) {
        this.idAleatoire = idAleatoire;
    }

    public String getIdAleatoire() {
        return idAleatoire;
    }
}
