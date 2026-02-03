package fr.miage.orleans.mac;

public class Main {

    public static void main(String[] args) {
        IPersonne p = new Nu();
        p.manger();
        p.respirer();
        p.sortir();

        p = new Chapeau(p);

        p.sortir();
        p.manger();
        p.respirer();
    }
}
