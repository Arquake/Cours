package org.example;

public class rechercheTableauTrie {

    public boolean rechercheDico(int[] t, int x) {

        int min, max, milieu;
        min = 0;
        max = t.length - 1;
        while (min < max) {
            milieu = (min + max) / 2;
            if (x > t[milieu]) {
                min= milieu + 1;
            } else {
                max =milieu;
            }
        }

        return t[min] == x;
    }

}
