package actions;

import modele.BonPlan;
import modele.TokenExpireException;
import modele.exceptions.BonPlanInconnuException;
import modele.exceptions.UtilisateurInconnuException;
import outils.DateConvertisseur;

import java.util.Collection;
import java.util.Optional;

public class GotoModifierBonPlan extends Environnement {

    private int idBonPlan;

    public int getIdBonPlan() {
        return idBonPlan;
    }

    public void setIdBonPlan(int idBonPlan) {
        this.idBonPlan = idBonPlan;
    }

    public Collection<BonPlan> getBonsPlans() throws TokenExpireException, UtilisateurInconnuException
    {
        return getFacadeBonsPlans().getBonsPlans(getToken(), Optional.empty());
    }

    BonPlan bonPlan;

    public String getDescription() {
        return bonPlan.getDescription();
    }

    public String getThematique() {
        return bonPlan.getThematique();
    }


    public String getDateDebut() {
        return DateConvertisseur.formatDate(bonPlan.getDateDebut());
    }






    public String getDateFin() {
        return DateConvertisseur.formatDate(bonPlan.getDateFin());
    }



    public String getLien() {
        return bonPlan.getLien();
    }



    public double getPrix() {
        return bonPlan.getPrix();
    }


    @Override
    public String execute() throws Exception {
        bonPlan = getFacadeBonsPlans().getBonPlanById(getToken(),idBonPlan );
        return SUCCESS;
    }


}
