import environment.Environment;
import facade.FacadeParisStaticImpl;
import modele.Match;
import modele.Utilisateur;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class PariForm extends Environment {
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
        super.FACADE.parier(
            ((Utilisateur) session.get("user")).getLogin(),
            idMatch,
            vainqueur,
            montant);
        return SUCCESS;

    }
}
