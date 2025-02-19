import environment.Environment;
import facade.FacadeParisStaticImpl;
import modele.Match;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Parier extends Environment {

    private long idMatch;
    private Match match;

    public void setIdMatch(long idMatch) {
        this.idMatch = idMatch;
    }

    @Override
    public String execute() throws Exception {
        if (session.containsKey("user")) {
            match = FACADE.getMatch(idMatch);
            return SUCCESS;
        }
        return ERROR;
    }

    public Match getMatch() {
        return match;
    }
}
