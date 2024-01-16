import exo1.*;

public class Main {
    public static void main(String[] args) {
        ClientBis premierClient = new ClientBis("Dupont");
        ClientBis deuxièmeClient = new ClientBis("Dupont");
        System.out.println(premierClient.getIdClient());
        System.out.println(deuxièmeClient.getIdClient());
    }
}