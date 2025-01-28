package fr.univ.orleans.pnt.actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.FacadeModele;

public class Etudiant extends ActionSupport {

    private static FacadeModele ETUDIANT_FACADE;

    public Etudiant() {
        ETUDIANT_FACADE = new FacadeModele();
    }

    public java.util.Collection<modele.Etudiant> getEtudiants() {
        return ETUDIANT_FACADE.getEtudiants();
    }

    @Override
    public String execute() throws Exception {
        return "showList";
    }
}
