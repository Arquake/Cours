import environment.Environment;
import facade.FacadeParisStaticImpl;
import modele.Pari;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class PariAnnule extends Environment {

    private long pariId;

    private Pari pari;

    @Override
    public String execute() throws Exception {
        if (session.containsKey("user")) {
            pari = FACADE.getPari(pariId);
            FACADE.annulerPari(((Utilisateur) session.get("user")).getLogin(), pariId);
            return SUCCESS;
        }
        return ERROR;
    }

    public Pari getPari() {
        return pari;
    }

    public void setPariId(long pariId) {
        this.pariId = pariId;
    }

    public Map<String, Object> getSession() {
        return session;
    }
}
