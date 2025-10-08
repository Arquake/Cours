package actions;

import modele.Pari;

import java.util.Collection;

public class GoMesParis extends Environnement {
    private Collection<Pari> paris;

    @Override
    public String execute() throws Exception {
        paris = getFacade().getMesParis(getUtilisateur().getLogin());
        return SUCCESS;
    }

    public Collection<Pari> getParis() {
        return paris;
    }
}
