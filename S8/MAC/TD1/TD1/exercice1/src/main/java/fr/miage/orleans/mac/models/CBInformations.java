package fr.miage.orleans.mac.models;

public class CBInformations implements IInformations {

    private String nom;
    private String noCarte;
    private String crypto;
    private String expire;

    public CBInformations(String nom, String noCarte, String crypto, String expire) {
        this.nom = nom;
        this.noCarte = noCarte;
        this.crypto = crypto;
        this.expire = expire;
    }

    @Override
    public void showInformations(int montant) {
        System.out.println("Paiement de " + montant + "€ par " + nom + "CB " + noCarte + "/" + crypto + " expire:" + expire);
    }
}
