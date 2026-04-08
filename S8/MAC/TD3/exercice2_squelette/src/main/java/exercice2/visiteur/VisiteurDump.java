package exercice2.visiteur;

import exercice2.formules.constantes.Faux;
import exercice2.formules.constantes.Inconnu;
import exercice2.formules.constantes.Vrai;
import exercice2.formules.operateurs.Et;
import exercice2.formules.operateurs.Negation;

/**
 * réprésente l'arbre sous la forme d'une chaine
 */
public class VisiteurDump implements Visiteur {

    private final StringBuilder s;

    public VisiteurDump() {
        this.s = new StringBuilder();
    }

    @Override
    public void visiterEt(Et et) {
        s.append("(");
        et.getDroite().accept(this);
        s.append(" AND ");
        et.getGauche().accept(this);
        s.append(")");
    }

    @Override
    public void visiterNegation(Negation neg) {
        s.append(" NOT ");
        neg.getFormule().accept(this);
    }

    @Override
    public void visiterVrai(Vrai vrai) {
        s.append(" TRUE ");
    }

    @Override
    public void visiterFaux(Faux faux) {
        s.append(" FALSE ");
    }

    @Override
    public void visiterInconnu(Inconnu inconnu) {
        s.append(" INCONNU ");
    }

    @Override
    public String toString() {
        return s.toString();
    }
}
