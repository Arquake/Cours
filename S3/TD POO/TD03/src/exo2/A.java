package exo2;

public class A {
    private long vA;
    public A() {
        vA = -1;
    }
    public A(long vA){
        this.vA = vA;
    }
    public long getvA(){
        return vA;
    }
    public double getValeur(){
        return vA;
    }
    public double somme (A a){
        return getValeur() + a.getValeur();
    }
    public String toString(){
        return "A(" +vA + ")";
    }
    public static double somme (A a1, A a2){
        return a1.somme(a2);
    }
}
