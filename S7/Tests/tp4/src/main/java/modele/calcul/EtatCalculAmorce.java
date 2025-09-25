package modele.calcul;

import facade.CalculatriceDynamiqueDuFuturImpl;
import modele.exceptions.NonSupporteeException;
import modele.exceptions.OperationMalFormeeException;
import modele.operations.Operations;

public class EtatCalculAmorce extends EtatCalcul{

    double operande1;
    String operationPrecedente;

    public EtatCalculAmorce(Operations operations, double operande1, String operationPrecedente) {
        super(operations);
        this.operande1 = operande1;
        this.operationPrecedente = operationPrecedente;
    }



    @Override
    public EtatCalcul calculCourant(String operation) throws NonSupporteeException, OperationMalFormeeException {
        double resultatEnCours =
                this.getOperations().getResultat(this.operationPrecedente,operande1,this.getOperandeCouranteValue());
        OperationHistorisee operationHistorisee = new OperationHistorisee(Double.toString(operande1),Double.toString(this.getOperandeCouranteValue()), CalculatriceDynamiqueDuFuturImpl.reverseTraduction.get(this.operationPrecedente),Double.toString(resultatEnCours));

        this.operande1 = resultatEnCours;
        this.getHistorique().add(operationHistorisee);
        if ("=".equals(operation)) {
            EtatCalcul e =  new EtatInitialCalcul(this.getOperations());
            e.setOperandeCourante(resultatEnCours);
            e.setHistorique(this.getHistorique());
            return e;
        }
        else {
            EtatCalcul e =  new EtatCalculAmorce(getOperations(),this.operande1,operation);
            e.setOperandeCourante(resultatEnCours);
            e.setHistorique(this.getHistorique());
            return e;
        }
    }


    @Override
    public double getResultat() {
        return operande1;
    }
}
