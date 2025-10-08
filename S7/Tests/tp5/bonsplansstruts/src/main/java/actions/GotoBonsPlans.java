package actions;

import modele.BonPlan;
import modele.TokenExpireException;
import modele.Utilisateur;
import modele.exceptions.DroitsInsuffisantsException;
import modele.exceptions.UtilisateurInconnuException;

import java.util.Collection;
import java.util.Optional;

public class GotoBonsPlans extends Environnement {

    public Collection<BonPlan> getBonsPlans() throws TokenExpireException, UtilisateurInconnuException
    {
        return getFacadeBonsPlans().getBonsPlans(getToken(), Optional.empty());
    }


    @Override
    public String execute() throws Exception {
            if (getBonsPlans().size() == 0) {
                addActionError("Aucun bon plan n'est disponible");
            }
        return SUCCESS;

    }
}
