import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParisStaticImpl;
import modele.Pari;
import modele.Utilisateur;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class MesParis extends ActionSupport implements SessionAware, ApplicationAware {

    private static FacadeParisStaticImpl FACADE;
    private Map<String, Object> session;

    private Collection<Pari> paris;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

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

    @Override
    public void setApplication(Map<String, Object> map) {
        FACADE = (FacadeParisStaticImpl) map.get("facade");
        if(Objects.isNull(FACADE)) {
            FACADE = new FacadeParisStaticImpl();
            map.put("facade", FACADE);
        }
    }
}
