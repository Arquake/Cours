package exercice2.formules.constantes;

import exercice2.formules.Formule;
import exercice2.visiteur.Visiteur;

public class Vrai implements Formule {

    @Override
    public void accept(Visiteur visiteur) {
        visiteur.visiterVrai(this);
    }
}
