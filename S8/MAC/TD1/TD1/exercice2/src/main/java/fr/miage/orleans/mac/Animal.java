package fr.miage.orleans.mac;

public class Animal {
    protected String nom;

    public Animal() {
    }

    public Animal(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
