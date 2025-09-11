package org.example;

import org.example.interfaces.TriTableau;

import java.util.Arrays;

public class TriRapide implements TriTableau {
    @Override
    public Integer[] trier(Integer[] t) {
        if (t.length == 1 || t.length == 0) {
            return t;
        }
        int mid = t.length/2;
        Integer[] g = this.trier(Arrays.copyOfRange(t, 0, mid));
        Integer[] d = this.trier(Arrays.copyOfRange(t, mid, t.length));
        int gIndex = 0;
        int dIndex = 0;
        Integer[] res = new Integer[t.length];
        while (gIndex < g.length && dIndex < d.length) {
            if (g[gIndex] < d[dIndex]) {
                res[gIndex+dIndex] = g[gIndex];
                gIndex++;
            }
            else {
                res[gIndex+dIndex] = d[dIndex];
                dIndex++;
            }
        }

        if (gIndex == g.length) {
            while (dIndex < d.length) {
                res[gIndex+dIndex] = d[dIndex];
                dIndex++;
            }
        } else {
            while (gIndex < g.length) {
                res[gIndex+dIndex] = g[gIndex];
                gIndex++;
            }
        }

        return res;
    }
}
