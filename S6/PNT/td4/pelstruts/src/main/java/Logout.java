import environment.Environment;
import facade.FacadeParisStaticImpl;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Logout extends Environment {

    @Override
    public String execute() throws Exception {
        FACADE.deconnexion(((Utilisateur) session.get("user")).getLogin());
        session.clear();
        return SUCCESS;
    }
}
