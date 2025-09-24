package org.example;

public class Syracuse {

    public long calculer(int x) {
        long res = 1;

        for (int i = 0; i < x; i++) {
            if (res%2 == 0) {
                res = res/2;
            }
            else {
                res = res * 3 +1;
            }
        }

        return res;
    }
}
