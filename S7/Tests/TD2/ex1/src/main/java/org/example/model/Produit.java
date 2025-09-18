package org.example.model;

import java.util.UUID;

public class Produit {

    private UUID id;
    private String nom;
    private Float prix;
    private Categorie categorie;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Produit(String nom, Float prix, Categorie categorie) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
    }

    public UUID getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix * categorie.getReduction();
    }

    public Produit() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Produit : " + nom + " | Prix : " + Math.round(prix*categorie.getReduction()/100)%0.01 + " | Id : " + id;
    }
}
