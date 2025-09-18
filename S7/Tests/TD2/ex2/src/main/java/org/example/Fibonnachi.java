package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Fibonnachi {

    Map<Integer, Integer> fibo;

    public Fibonnachi() {
        this.fibo = new HashMap<>();
        fibo.put(0,1);
        fibo.put(1,1);
    }

    public Integer getValue(int n) throws InvalidvalueException {
        if (n<0) { throw new InvalidvalueException(); }
        if (fibo.get(n) != null) { return fibo.get(n); }
        Integer n1 = fibo.get(n-1);
        Integer n2 = fibo.get(n-2);
        if (Objects.isNull(n1)) {
            n1 = getValue(n-1);
            fibo.put(n-1, n1);
        }
        if (Objects.isNull(n2)) {
            n2 = getValue(n-2);
            fibo.put(n-2, n2);
        }
        return n1 + n2;
    }
}
