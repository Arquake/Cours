package exercice2.visiteur;

import exercice2.formules.constantes.Faux;
import exercice2.formules.constantes.Inconnu;
import exercice2.formules.constantes.Vrai;
import exercice2.formules.operateurs.Et;
import exercice2.formules.operateurs.Negation;

public class VisiteurEvaluation implements Visiteur {
    @Override
    public void visiterEt(Et et) {

    }

    @Override
    public void visiterNegation(Negation neg) {

    }

    @Override
    public void visiterVrai(Vrai vrai) {

    }

    @Override
    public void visiterFaux(Faux faux) {

    }

    @Override
    public void visiterInconnu(Inconnu inconnu) {

    }
}
