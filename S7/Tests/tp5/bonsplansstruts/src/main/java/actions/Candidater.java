package actions;

import modele.TokenExpireException;
import modele.exceptions.CandidatureDejaDeposeeException;
import modele.exceptions.UtilisateurInconnuException;

public class Candidater extends Environnement {

    @Override
    public String execute() throws Exception {
        try {
            getFacadeBonsPlans().candidaterModerateur(getToken());
            addActionMessage("Candidature envoyée !");
            return SUCCESS;
        }
        catch ( UtilisateurInconnuException | TokenExpireException e) {
            addActionError(e.getMessage());
            return ERROR;
        }

        catch ( CandidatureDejaDeposeeException e) {
            addActionError("Candidature déja envoyée");
            return INPUT;
        }

    }
}
