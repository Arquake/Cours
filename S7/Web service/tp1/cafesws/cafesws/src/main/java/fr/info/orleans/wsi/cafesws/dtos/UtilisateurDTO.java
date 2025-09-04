package fr.info.orleans.wsi.cafesws.dtos;

import java.io.Serializable;

public class UtilisateurDTO implements Serializable {
    private String nom;
    private String prenom;
    private String email;

    public UtilisateurDTO() {
    }

    public UtilisateurDTO(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
