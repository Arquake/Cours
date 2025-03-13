package controleur;

import controleur.ordres.LanceurOrdre;
import vues.GestionnaireVue;

public interface Controleur extends LanceurOrdre {
    GestionnaireVue getGestionnaireVue();
    void run();
}
