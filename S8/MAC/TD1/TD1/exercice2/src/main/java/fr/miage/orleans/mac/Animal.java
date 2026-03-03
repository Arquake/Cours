package fr.miage.orleans.mac;

import java.util.Objects;

public abstract class Animal {
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

    @Override
    public boolean equals(Object obj) {
        if (Objects.isNull(obj)) return false;
        if (obj.getClass() != this.getClass()) return false;
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return getNom().hashCode();
    }
}
