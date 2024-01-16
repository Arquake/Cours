package exo6;

public class Main {
    public static void main(String[] args) {
        int[] tableau = {1, 2, 3, 4, 5};
        for (int v : tableau)
            System.out.print(v);
        System.out.println();
        B b = new B();
        int[] t = b.getTab();
        for (int v : t)
            System.out.print(v);
        System.out.println();
        t[0] = 1;
        System.out.println(b.get(0));
        b.m(tableau);
        for (int v : tableau)
            System.out.print(v);
        System.out.println();
        tableau[3]= 6;
        for (int v : tableau)
            System.out.print(v);
        System.out.println();
        System.out.println(b.get(3));
    }
}
