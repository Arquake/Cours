package exercice2.programme;

import exercice2.formules.*;
import exercice2.formules.constantes.Faux;
import exercice2.formules.constantes.Inconnu;
import exercice2.formules.constantes.Vrai;
import exercice2.formules.operateurs.Et;
import exercice2.visiteur.VisiteurDump;
import exercice2.visiteur.VisiteurEvaluation;

public class Programme {
    public static void main(String[] args) {
        /* TODO --- Formule f = ... */
        Formule f = new Et(new Inconnu(), new Et(new Vrai(), new Faux()));
        VisiteurEvaluation evaluation = new VisiteurEvaluation();
        f.accept(evaluation);
        System.out.println(evaluation);

        VisiteurDump affichage = new VisiteurDump();
        f.accept(affichage);
        System.out.println(affichage);
    }
}
