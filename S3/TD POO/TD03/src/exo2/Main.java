package exo2;

public class Main {
    public static void main(String[] args) {
        A.somme(new A() , new A());
        (new A()).somme(new D());
        B.somme(new B() , new B());
        (new B()).somme(new C());
        C.somme(new D() , new D( ));
        (new C()).somme(new B());
        C.somme(new A() , new B()) ;
        (new D()).somme(new A());
        D.somme(new B() , new A());
    }
}
