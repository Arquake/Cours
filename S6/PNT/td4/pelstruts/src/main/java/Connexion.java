import facade.FacadeParisStaticImpl;
import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Connexion extends ActionSupport implements SessionAware, ApplicationAware {

    private String login;
    private String password;

    private static FacadeParisStaticImpl FACADE;
    private Map<String, Object> session;

    @Override
    public String execute() throws Exception {
        try {
            if(session.containsKey("user")) {
                return SUCCESS;
            }
            Utilisateur user = FACADE.connexion(login ,password);
            session.put("user", user);
            return SUCCESS;
        } catch (UtilisateurDejaConnecteException e) {
            addActionError("Couple pseudo/password incohérent");
        } catch (InformationsSaisiesIncoherentesException e) {
            addActionError("Le pseudo est obligatoire et de taille 2 min. Le mot de passe est obligatoire et de taille 2 min");
        }
        return INPUT;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logout() {
        try {
            FACADE.deconnexion(((Utilisateur) session.get("user")).getLogin());
            session.clear();
        }
        catch (Exception ignore) {}
        return SUCCESS;
    }

    @Override
    public void withApplication(Map<String, Object> map) {
        FACADE = (FacadeParisStaticImpl) map.get("facade");
        if(Objects.isNull(FACADE)) {
            FACADE = new FacadeParisStaticImpl();
            map.put("facade", FACADE);
        }
    }

    @Override
    public void withSession(Map<String, Object> session) {
        this.session = session;
    }
}
