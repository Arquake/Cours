package exo1;

import java.util.Scanner;

public class Div {
    private double x;
    public Div(double x) {
        this.x=x;
    }
    public double getX(){
        return x;
    }
    public void m1(double y) throws E1,E2{
        System.out.println("Debut de m1");
        if (y==0)
            throw new E1("Division par 0 impossible");
        if (x*y<0)
            throw new E2("Signes opposés interdits");
        x=x/y;
        System.out.println("Fin de m1");
    }
    public void m2(double y) throws E1{
        System.out.println("Debut de m2");
        try {
            System.out.println("Dans m2 avant appel de m1");
            m1(y);
            System.out.println("Dans m2 après appel de m1");
        }catch (E2 e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Fin de m2");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choisir le premier paramètre");
        double v1 = in.nextDouble();
        Div d = new Div(v1);
        System.out.println("Choisir le deuxième paramètre");
        double v2 = in.nextDouble();
        try {
            d.m2(v2);
        }catch (E1 e) {
            System.out.println(e.getMessage());
        }
        System.out.println("x= "+ d.getX());
    }
}


