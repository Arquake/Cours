package actions;

import facade.exceptions.ResultatImpossibleException;
import modele.Match;

public class Resultat extends Environnement {
    // variable en entrée
    private long idMatch;
    private String resultat;

    // variables en sortie
    private Match match;

    public void setIdMatch(long idMatch) {
        this.idMatch = idMatch;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public long getIdMatch() {
        return idMatch;
    }

    public Match getMatch() {
        return match;
    }

    @Override
    public String execute() throws Exception {
        try {
            getFacade().resultatMatch(getUtilisateur().getLogin(), idMatch, resultat);
        } catch (ResultatImpossibleException e) {
            addFieldError("resultat", "resultat "+resultat+" impossible pour ce match.");
            return INPUT;
        }
        match = getFacade().getMatch(idMatch);
        return SUCCESS;
    }
}
