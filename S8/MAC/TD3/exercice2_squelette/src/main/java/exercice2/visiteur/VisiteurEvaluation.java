package exercice2.visiteur;

import exercice2.formules.Formule;
import exercice2.formules.constantes.Faux;
import exercice2.formules.constantes.Inconnu;
import exercice2.formules.constantes.Vrai;
import exercice2.formules.operateurs.Et;
import exercice2.formules.operateurs.Negation;

public class VisiteurEvaluation implements Visiteur {

    Formule f;

    public VisiteurEvaluation() {

    }

    @Override
    public void visiterEt(Et et) {
        et.getDroite().accept(this);
        Formule membreDroit = f;
        et.getGauche().accept(this);
        Formule membreGauche = f;

        if (membreDroit instanceof Faux || membreGauche instanceof Faux) {
            f = new Faux();
        }
         else if (membreDroit instanceof Inconnu || membreGauche instanceof Inconnu) {
            f = new Vrai();
        }
         else {
             f = new Vrai();
        }
    }

    @Override
    public void visiterNegation(Negation neg) {
        neg.accept(this);

        if (f instanceof Faux) {
            f = new Vrai();
        }
        else if (f instanceof Inconnu) {
            f = new Inconnu();
        }
        else {
            f = new Faux();
        }
    }

    @Override
    public void visiterVrai(Vrai vrai) {
        f = vrai;
    }

    @Override
    public void visiterFaux(Faux faux) {
        f = faux;
    }

    @Override
    public void visiterInconnu(Inconnu inconnu) {
        f = inconnu;
    }

    @Override
    public String toString() {
        if (f instanceof Vrai) {
            return "VRAI";
        }
        if (f instanceof Faux) {
            return "FAUX";
        }
        return "INCONNU";
    }
}
