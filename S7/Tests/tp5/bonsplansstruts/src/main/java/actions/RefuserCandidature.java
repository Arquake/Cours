package actions;

import modele.TokenExpireException;
import modele.Utilisateur;
import modele.exceptions.CandidatureInconnueException;
import modele.exceptions.DroitsInsuffisantsException;
import modele.exceptions.UtilisateurInconnuException;

import java.util.Collection;

public class RefuserCandidature extends Environnement {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Utilisateur> getCandidatures() throws TokenExpireException, UtilisateurInconnuException, DroitsInsuffisantsException { return getFacadeBonsPlans().getListeCandidatureModerateur(getToken()); } // Collection<Utilisateur>

    @Override
    public String execute() throws Exception {
        try {
            getFacadeBonsPlans().refuserModerateur(getToken(), email);
            addActionMessage("Candidature refusée !");
            return SUCCESS;
        }
        catch ( UtilisateurInconnuException | TokenExpireException e) {
            addActionError("Vous devez vous authentifier !");
            return ERROR;
        }

        catch ( CandidatureInconnueException e) {
            addActionError("Candidature Inexistante !");
            return INPUT;
        }
    }
}
