package models.operands;

import models.AOperand;
import models.IOperation;
import models.IVisiteur;
import models.Visiteur;

public class Multiplier extends AOperand {

    public Multiplier(IOperation left, IOperation right) {
        super(left, right);
    }

    @Override
    public void accept(IVisiteur v) {
        v.visiterMultiplier(this);
    }
}
