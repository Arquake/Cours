import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParisStaticImpl;
import modele.Match;
import modele.Utilisateur;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
import java.util.Objects;

public class PariForm extends ActionSupport implements ApplicationAware, SessionAware {
    private long idMatch;
    private Match match;
    private String vainqueur;
    private double montant;

    public Match getMatch() {
        return match;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setVainqueur(String vainqueur) {
        this.vainqueur = vainqueur;
    }

    private Map<String, Object> session;
    private static FacadeParisStaticImpl FACADE;

    public void setIdMatch(long idMatch) {
        this.idMatch = idMatch;
    }

    @Override
    public String execute() throws Exception {
        if (session.containsKey("user") && FACADE.getMatch(idMatch) != null) {
            try {
                match = FACADE.getMatch(idMatch);
                if (!vainqueur.equals(match.getEquipe1()) && !vainqueur.equals(match.getEquipe2()) ) {
                    addActionError("Vous devez choisir une équipe valide");
                    throw new Exception("Equipe invalide");
                }
                else if (montant <= 0) {
                    addActionError("Mise inférieure ou égal à 0");
                    throw new Exception("Mise nul");
                }
                FACADE.parier(
                        ((Utilisateur) session.get("user")).getLogin(),
                        idMatch,
                        vainqueur,
                        montant);
                return SUCCESS;
            }
            catch (Exception ignore) {
                return "parierError";
            }
        }
        return ERROR;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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
