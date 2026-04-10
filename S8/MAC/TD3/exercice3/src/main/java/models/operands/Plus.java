package models.operands;

import models.AOperand;
import models.IOperation;
import models.IVisiteur;
import models.Visiteur;

public class Plus extends AOperand {

    public Plus(IOperation left, IOperation right) {
        super(left, right);
    }

    @Override
    public void accept(IVisiteur v) {
        v.visiterPlus(this);
    }
}
