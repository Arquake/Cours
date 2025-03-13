package controleur;

import controleur.abstractControleur.AbstractControleur;
import controleur.ordres.TypeOrdre;
import modele.Cheval;
import modele.ChevalDejaExistant;
import modele.ChevalNotFound;
import modele.FacadeModele;
import vues.GestionnaireVue;

import java.util.List;


public class ControleurImpl extends AbstractControleur {

    private FacadeModele facadeModele;

    public ControleurImpl(GestionnaireVue gestionnaireVue, FacadeModele facadeModele, ControleurSetUp controleurSetUp) {
        super(gestionnaireVue);
        this.facadeModele = facadeModele;
        controleurSetUp.setUp(this, getGestionnaireVue());
    }

    @Override
    public void run() {
        this.fireOrdre(TypeOrdre.LOAD_CHEVAUX);
        this.fireOrdre(TypeOrdre.SHOW_CHEVAUX);
    }


    public void goToChevaux() {
        this.fireOrdre(TypeOrdre.SHOW_CHEVAUX);

    }

    private Cheval chevalSelectionne;

    public void goToDetailCheval(long idCheval) {
        try {
            this.chevalSelectionne = this.facadeModele.getChevalById(idCheval);
            this.fireOrdre(TypeOrdre.SELECTION_CHEVAL);
            this.fireOrdre(TypeOrdre.SHOW_DETAIL);

        } catch (ChevalNotFound chevalNotFound) {
            throw new RuntimeException("TODO");
        }

    }

    public Cheval getChevalSelectionne() {
        return chevalSelectionne;
    }

    public void goToAjouterCheval() {
        this.fireOrdre(TypeOrdre.SHOW_ENREGISTREMENT);

    }

    public List<Cheval> getChevaux() {
        return this.facadeModele.getAll();
    }

    public void enregistrerCheval(String nom, int age) {
        try {
            this.facadeModele.ajouterCheval(nom,age);
            this.fireOrdre(TypeOrdre.NOUVEAU_CHEVAL);
        } catch (ChevalDejaExistant chevalDejaExistant) {
            this.fireOrdre(TypeOrdre.ERREUR_CHEVAL_EXISTANT);
        }
    }
}
