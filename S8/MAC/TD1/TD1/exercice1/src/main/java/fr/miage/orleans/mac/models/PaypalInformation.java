package fr.miage.orleans.mac.models;

public class PaypalInformation implements IInformations {

    private String email;
    private String numeroUtilisation;

    public PaypalInformation(String email, String numeroUtilisation) {
        this.email = email;
        this.numeroUtilisation = numeroUtilisation;
    }

    @Override
    public void showInformations(int montant) {
        System.out.println("Paiement de " + montant + "€ sur " + email + " avec le code " + numeroUtilisation);
    }
}
