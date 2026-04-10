package models;

import models.operands.Diviser;
import models.operands.Moins;
import models.operands.Multiplier;
import models.operands.Plus;

public interface IVisiteur {

    int getValue();

    void visiterMoins(Moins m);
    void visiterPlus(Plus p);
    void visiterMultiplier(Multiplier m);
    void visiterDiviser(Diviser d);
    void visiterDigit(Digit d);
}
