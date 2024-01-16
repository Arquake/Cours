package exo2;

public class C extends A {
    private int vC;
    public C() {
        vC=-3;
    }
    public C(int vC) {
        super(vC + 1);
        this.vC = vC;
    }
    public C(long vA, int vC) {
        super(vA);
        this.vC = vC;
    }
    public int getvC(){
        return vC;
    }
    public double getValeur(){
        return vC;
    }
    public double somme (C c){
        return super.somme(c) + 1;
    }
    public String toString(){
        return "C<" +super.toString()
                + ">(" + vC + ")";
    }
}
