package actions;

import facade.exceptions.OperationNonAuthoriseeException;
import modele.Pari;

public class AnnulerPari extends Environnement {
    // variable en entrée
    private long idPari;

    // variables en sortie
    private Pari pari;

    public void setIdPari(long idPari) {
        this.idPari = idPari;
    }

    public Pari getPari() {
        return pari;
    }

    @Override
    public String execute() throws Exception {
        pari = getFacade().getPari(idPari);
        try {
            getFacade().annulerPari(getUtilisateur().getLogin(),idPari);
        } catch (OperationNonAuthoriseeException e) {
            //addFieldError("idPari", "vous n'avez pas les droits nécessaires.");
            addActionError("Annulation impossible.");
            return INPUT;
        }
        return SUCCESS;
    }

}
