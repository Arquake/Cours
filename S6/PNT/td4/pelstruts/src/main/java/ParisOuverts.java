
import environment.Environment;
import facade.FacadeParisStaticImpl;
import modele.Match;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ParisOuverts extends Environment {

    private Collection<Match> matchs;

    @Override
    public String execute() throws Exception {
        if (session.containsKey("user")) {
            matchs = FACADE.getMatchsPasCommences();
            return SUCCESS;
        }
        return ERROR;
    }

    public Collection<Match> getMatchs() {
        return matchs;
    }

    public Map<String, Object> getSession() {
        return session;
    }
}
