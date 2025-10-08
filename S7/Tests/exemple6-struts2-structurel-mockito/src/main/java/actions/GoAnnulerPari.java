package actions;

import modele.Pari;

public class GoAnnulerPari extends Environnement {
    // en entrée
    private long idPari;

    // en sortie
    private Pari pari;


    public void setIdPari(long idPari) {
        this.idPari = idPari;
    }

    public long getIdPari() {
        return idPari;
    }

    public Pari getPari() {
        return pari;
    }

    @Override
    public String execute() throws Exception {
        pari = getFacade().getPari(idPari);
        return SUCCESS;
    }
}
