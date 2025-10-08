package modele;

import java.util.Collection;

public interface Utilisateur {

    void nouveauBonPlanPropose(BonPlan bonPlan);

    void setRole(String moderateur);

    enum Role { MODERATEUR, UTILISATEUR };

    String getEmail();

    String getMotDePasse();

    String getNom();

    String getPrenom();

    Collection<BonPlan> getBonsPlansProposes();

    String getRole();
}
