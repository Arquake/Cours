package exercice2.visiteur;

import exercice2.formules.constantes.Faux;
import exercice2.formules.constantes.Inconnu;
import exercice2.formules.constantes.Vrai;
import exercice2.formules.operateurs.Et;
import exercice2.formules.operateurs.Negation;

public interface Visiteur {

    void visiterEt(Et et);
    void visiterNegation(Negation neg);
    void visiterVrai(Vrai vrai);
    void visiterFaux(Faux faux);
    void visiterInconnu(Inconnu inconnu);

}
