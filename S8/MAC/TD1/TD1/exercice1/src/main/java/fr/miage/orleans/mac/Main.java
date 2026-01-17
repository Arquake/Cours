package fr.miage.orleans.mac;

import fr.miage.orleans.mac.models.*;

public class Main {
    public static void main(String[] args) {
        Panier panierUn = new Panier();

        for (int i = 0; i < 8; i++) {
            panierUn.addItem(new Corrige());
        }

        IInformations paypal = new PaypalInformation("moi@moi.moi", "kys");
        IInformations cb = new CBInformations("moi", "1234567890123456", "374", "15min");

        panierUn.setMoyenDePaiement(paypal);
        panierUn.pay();
        panierUn.setMoyenDePaiement(cb);
        panierUn.pay();
    }
}
