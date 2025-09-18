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
            int i = 0;
            while (it.hasNext()) {
                X courant = it.next();
                X suivant;
                if (it.hasNext()) {
                    suivant = it.next();
                    if (suivant != null && suivant.compareTo(courant) < 0) {
                        resultat.add(suivant);
                        resultat.add(courant);
                        modifie = true;
                    } else {
                        if (suivant.compareTo(courant)>0) {
                            resultat.add(courant);
                            it.previous();
                        }
                    }
                }
                else {
                    resultat.add(courant);
                }
            }//while (it.hasNext())
            l = resultat;
        } while (modifie);
        return resultat;
    }

}
