package fr.miage.orleans.mac;

public class Main {

    public static void main(String[] args) {
        IPersonne p = new Nu();
        p.manger();
        p.respirer();

        Chapeau c = new Chapeau(p);

        c.sortir();
        c.manger();
        c.respirer();
    }
}
