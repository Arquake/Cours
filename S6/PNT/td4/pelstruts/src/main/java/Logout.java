import facade.FacadeParisStaticImpl;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Logout extends ActionSupport implements SessionAware, ApplicationAware {

    private Map<String, Object> session;

    private FacadeParisStaticImpl FACADE;


    @Override
    public String execute() throws Exception {
        FACADE.deconnexion(((Utilisateur) session.get("user")).getLogin());
        session.clear();
        return SUCCESS;
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
