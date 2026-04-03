package exercice2.formules.operateurs;

import exercice2.formules.Formule;
import exercice2.visiteur.Visiteur;

public class Negation implements Formule {

    private Formule formule;

    public Negation(Formule formule) {
        this.formule = formule;
    }

    public Formule getFormule() {
        return formule;
    }

    @Override
    public void accept(Visiteur visiteur) {
        visiteur.visiterNegation(this);
    }
}
