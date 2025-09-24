package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Fibonacci {

    public long calculer(int n) throws InvalidValueException {
        if (n<0) { throw new InvalidValueException(); }
        if (fibo.get(n) != null) { return fibo.get(n); }
        Long n1 = fibo.get(n-1);
        Long n2 = fibo.get(n-2);
        if (Objects.isNull(n1)) {
            n1 = calculer(n-1);
            fibo.put(n-1, n1);
        }
        if (Objects.isNull(n2)) {
            n2 = calculer(n-2);
            fibo.put(n-2, n2);
        }
        return n1 + n2;
    }

    Map<Integer, Long> fibo;

    public Fibonacci() {
        this.fibo = new HashMap<>();
        fibo.put(0,Integer.toUnsignedLong(1));
        fibo.put(1,Integer.toUnsignedLong(1));
    }
}

