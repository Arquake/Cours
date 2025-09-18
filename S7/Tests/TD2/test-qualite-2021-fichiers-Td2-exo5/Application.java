package td2Exo5;

import java.util.List;

public class Application {

    public static double computeAverage(List<INote> collection) {

        int nbElement = 0;
        double resultat =0D;

        if (collection.size()>0) {
            int i = 1;
            resultat = collection.get(0).getNote();
            nbElement = 1;

            while (i<collection.size()) {
                INote element = collection.get(i);
                resultat = resultat + element.getNote();
                nbElement++;
                i++;
            }
        }


        if (nbElement >0) {
            resultat = resultat / nbElement;

        }
        return resultat;

    }

}
