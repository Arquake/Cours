import facade.FacadeParisStaticImpl;
import modele.Pari;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class PariAnnule extends ActionSupport implements SessionAware, ApplicationAware {

    private Map<String, Object> session;

    private long pariId;

    private Pari pari;

    private static FacadeParisStaticImpl FACADE;

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

    @Override
    public void withSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void withApplication(Map<String, Object> map) {
        FACADE = (FacadeParisStaticImpl) map.get("facade");
        if(Objects.isNull(FACADE)) {
            FACADE = new FacadeParisStaticImpl();
            map.put("facade", FACADE);
        }
    }
}
