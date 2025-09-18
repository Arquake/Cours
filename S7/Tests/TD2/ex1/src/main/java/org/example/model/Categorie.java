package org.example.model;

public class Categorie {

    private int id;

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = 1 - reduction;
    }

    private double reduction;

    public Categorie() {
    }

    public Categorie(int id, double reduction) {
        this.id = id;
        this.reduction = 1 - reduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
