package org.example;

import org.example.interfaces.TriTableau;

public class TriBulleTableau implements TriTableau {


    @Override
    public Integer[] trier(Integer[] t) {
        Integer[] clonedTab = t.clone();
        int temp = 0;
        for (int i = 0; i <clonedTab.length; i++) {
            for (int j = i+1; j < clonedTab.length; j++) {
                if (clonedTab[i] > clonedTab[j]) {
                    temp = clonedTab[j];
                    clonedTab[j] = clonedTab[i];
                    clonedTab[i] = temp;
                }
            }
        }
        return clonedTab;
    }
}
