package exo2;

public class A {
    private int a;
    public void f(int a){
        System.out.println(a);
    }

    public int getA() {
        return a;
    }

    A(){
        a=0;
    }

    A(int a){
        this.a = a;
    }
}
