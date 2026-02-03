package programme;

import fibonacci.*;

public class Main {
    public static void main(String[] args) throws TermeInexistantException {
        Fibonacci fibonacci = new FibonacciBasique();


        long t0 = System.currentTimeMillis();
        System.out.println(fibonacci.compute(40));
        long t1 = System.currentTimeMillis();
        System.out.println("Premier calcul :" + (t1-t0)+" millisecondes");



    }
}
