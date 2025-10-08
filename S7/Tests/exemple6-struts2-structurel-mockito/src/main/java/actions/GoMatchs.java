package actions;

import modele.Match;

import java.util.Collection;

public class GoMatchs extends Environnement {
    private Collection<Match> matchs;

    @Override
    public String execute() throws Exception {
        matchs = getFacade().getAllMatchs();
        return SUCCESS;
    }

    public Collection<Match> getMatchs() {
        return matchs;
    }
}
