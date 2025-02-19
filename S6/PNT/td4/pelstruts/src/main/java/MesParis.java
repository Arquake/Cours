import environment.Environment;
import facade.FacadeParisStaticImpl;
import modele.Pari;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class MesParis extends Environment {

    private Collection<Pari> paris;

    @Override
    public String execute() throws Exception {
        if (session.containsKey("user")) {
            paris = FACADE.getMesParis(((Utilisateur) session.get("user")).getLogin());
            return SUCCESS;
        }
        return ERROR;
    }

    public Collection<Pari> getParis() {
        return paris;
    }

    public String annulerPari() {
        if (session.containsKey("user")) {
            paris = FACADE.getMesParis(((Utilisateur) session.get("user")).getLogin());
            return SUCCESS;
        }
        return ERROR;
    }

    public Map<String, Object> getSession() {
        return session;
    }
}
