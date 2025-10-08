package actions;

import modele.Match;

import java.util.Collection;

public class GoMatchsOuvertAuxParis extends Environnement {
    private Collection<Match> matchs;

    @Override
    public String execute() throws Exception {
        matchs = getFacade().getMatchsPasCommences();
        return SUCCESS;
    }

    public Collection<Match> getMatchs() {
        return matchs;
    }
}
