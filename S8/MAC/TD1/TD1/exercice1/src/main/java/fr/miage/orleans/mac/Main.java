package fr.miage.orleans.mac;

import fr.miage.orleans.mac.models.Corrige;
import fr.miage.orleans.mac.models.Panier;

public class Main {
    public static void main(String[] args) {
        Panier panierUn = new Panier();

        for (int i = 0; i < 8; i++) {
            panierUn.addItem(new Corrige());
        }

        panierUn.pay("moi", "1234567890123456", "374", "15min");
        panierUn.payByPaypal("moi@moi.moi", "kys");
    }
}
