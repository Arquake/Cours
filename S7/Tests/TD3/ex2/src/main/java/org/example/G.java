package org.example;

public class G {

    public G() {
    }

    public long calculer(long resFibo, long resSyra, Cut cut) {
        long so = resFibo + resSyra;
        if (cut.getValue() > so) {
            return so;
        }
        return Math.max(resSyra, resFibo);
    }

}
