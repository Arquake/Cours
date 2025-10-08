package actions;

import modele.Match;

import java.util.ArrayList;
import java.util.List;

public class GoResultat extends Environnement {
    private long idMatch;
    private Match match;
    private List<String> resultatsPossibles;

    public void setIdMatch(long idMatch) {
        this.idMatch = idMatch;
    }

    public long getIdMatch() {
        return idMatch;
    }

    public Match getMatch() {
        return match;
    }

    public List<String> getResultatsPossibles() {
        return resultatsPossibles;
    }

    @Override
    public String execute() throws Exception {
        match = getFacade().getMatch(idMatch);
        resultatsPossibles = new ArrayList<>();
        resultatsPossibles.add("nul");
        resultatsPossibles.add(match.getEquipe1());
        resultatsPossibles.add(match.getEquipe2());
        return SUCCESS;
    }
}
