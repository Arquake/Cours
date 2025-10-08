package actions;

import modele.TokenExpireException;
import modele.Utilisateur;
import modele.exceptions.DroitsInsuffisantsException;
import modele.exceptions.UtilisateurInconnuException;

import java.util.Collection;

public class GotoCandidatures extends Environnement {

    public Collection<Utilisateur> getCandidatures() throws TokenExpireException, UtilisateurInconnuException, DroitsInsuffisantsException { return getFacadeBonsPlans().getListeCandidatureModerateur(getToken()); } // Collection<Utilisateur>

}
