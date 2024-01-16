package exo2;

public class B extends A {
    private double vB;
    public B() {
        vB = -2;
    }
    public B(double vB) {
        this.vB = vB;
    }
    public B(long vA, double vB) {
        super(vA);
        this.vB = vB;
    }
    public double getvB(){
        return vB;
    }
    public double getValeur(){
        return vB;
    }
    public double somme (A a){
        return somme (this,a);
    }
}
