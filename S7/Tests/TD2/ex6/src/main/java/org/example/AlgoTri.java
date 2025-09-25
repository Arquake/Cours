package td2Exo6;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AlgoTri <X extends Comparable > {


    public List<X> tri(List<X> l) {

        boolean modifie;
        List<X> resultat;
        do {
            modifie = false;
            resultat = new ArrayList<X>();
            ListIterator<X> it = l.listIterator();
            while (it.hasNext()) { // conditionnel, 19: tant que it a un object suivant
                X courant = it.next();
                X suivant;
                if (it.hasNext()) { // conditionnel, si it a un élément disponible après
                    suivant = it.next();
                    if (suivant != null && suivant.compareTo(courant) < 0) { // conditionnel, si suivant !null et suivant < courant
                        resultat.add(suivant);
                        resultat.add(courant);
                        modifie = true;
                    } else { // conditionnel, si suivant == null ou suivant >= courant
                        if (suivant.compareTo(courant)>0) { // si suivant > courant
                            resultat.add(courant);
                            it.previous();
                        }
                    }
                }
                else { // conditionnel, il n'y a pas d'objet suivant
                    resultat.add(courant);
                }
            }
            l = resultat;
        } while (modifie); // conditionnel, si modifie == true
        return resultat;
    }

}
