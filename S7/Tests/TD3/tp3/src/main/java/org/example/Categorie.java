package org.example;

public class Categorie {

    public int valCategorie(int an) {
        if (an <= 10) {
            return 1;
        }
        if (an <= 20) {
            return 2;
        }
        return 3;
    }
}
