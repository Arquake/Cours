import facade.FacadeParisStaticImpl;
import modele.Match;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

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
    public void validate() {
        match = FACADE.getMatch(idMatch);
        if (match == null) {
           addActionError("Le Match n'existe pas");
        }
        else if (!vainqueur.equals(match.getEquipe1()) && !vainqueur.equals(match.getEquipe2()) ) {
            addActionError("Vous devez choisir une équipe valide");
        }
        else if (montant <= 0) {
            addActionError("Mise inférieure ou égal à 0");
        }
    }

    @Override
    public String execute() throws Exception {
        if (hasActionErrors()) {
            return INPUT;
        }
        if (!session.containsKey("user") && FACADE.getMatch(idMatch) == null) {
            return ERROR;
        }
        FACADE.parier(
            ((Utilisateur) session.get("user")).getLogin(),
            idMatch,
            vainqueur,
            montant);
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
