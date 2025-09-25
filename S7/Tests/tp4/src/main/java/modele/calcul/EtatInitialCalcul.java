package modele.calcul;

import modele.exceptions.NonSupporteeException;
import modele.exceptions.OperationMalFormeeException;
import modele.operations.Operations;

public class EtatInitialCalcul extends EtatCalcul{


    public EtatInitialCalcul(Operations operations) {
        super(operations);
        this.setOperandeCourante(0d);
    }

    @Override
    public EtatCalcul calculCourant(String operation) throws  OperationMalFormeeException {
        if ("=".equals(operation)) {
            this.setOperandeCourante(this.getResultat());
            return this;
        }
        else {
            EtatCalcul e = new EtatCalculAmorce(this.getOperations(), this.getOperandeCouranteValue(), operation);
            e.setHistorique(this.getHistorique());
            return e;
        }
    }
}
