package exercice2.formules.operateurs;

import exercice2.formules.Formule;

public class Et implements Formule {

    private Formule gauche;
    private Formule droite;

    public Et(Formule gauche, Formule droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    public Formule getGauche() {
        return gauche;
    }

    public Formule getDroite() {
        return droite;
    }
}
