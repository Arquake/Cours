package mystere;

import java.util.List;

public class OutilsListe {


    // liste soumises aux différentes opérations proposées par la classe
    private List<Integer> list;

    public OutilsListe(List<Integer> list) {
        this.list = list;
    }


    /**
     * Permet de vérifier si un élément x est dans la liste ou pas
     * @param x
     * @return
     */

    public boolean estElementDe(int x) {
        boolean trouve=false;
        int taille=list.size();
        if (taille!=0){
            int cpt=0;
            while (!trouve && cpt<taille-1){
                if (x==list.get(cpt)){
                    trouve=true;
                }
                cpt=cpt+1;
            }
        }
        return trouve;
    }
}
