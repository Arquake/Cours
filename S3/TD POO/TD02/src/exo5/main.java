package exo5;

public class main {
    public static void main(String[] args) {
        Horaire h1 = new Horaire(1000);
        Horaire h2 = new Horaire(1000);
        Horaire h3 = new Horaire(1500);
        Horaire h4 = new Horaire(500);

        System.out.println(h1.compareTo(h2));
        System.out.println(h1.compareTo(h3));
        System.out.println(h1.compareTo(h4));
    }
}
