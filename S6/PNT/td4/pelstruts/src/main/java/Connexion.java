import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParisStaticImpl;
import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;

public class Connexion extends ActionSupport {

    private String login;
    private String password;

    private static FacadeParisStaticImpl FACADE;

    public Connexion() {
        if (FACADE == null) {
            FACADE = new FacadeParisStaticImpl();
        }
    }

    @Override
    public String execute() throws Exception {
        try {
            FACADE.connexion(login ,password);
        } catch (UtilisateurDejaConnecteException e) {
            addActionError("Couple pseudo/password incohérent");
        } catch (InformationsSaisiesIncoherentesException e) {
            addActionError("Le pseudo est obligatoire et de taille 2 min. Le mot de passe est obligatoire et de taille 2 min");
        }
        if (hasActionErrors()){
            return INPUT;
        }
        return SUCCESS;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
