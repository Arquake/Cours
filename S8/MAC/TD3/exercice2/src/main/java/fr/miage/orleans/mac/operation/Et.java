package fr.miage.orleans.mac.operation;

import fr.miage.orleans.mac.CustomBooleans;

public class Et extends BiOperation {

    public Et(Operation op1, Operation op2) {
        super(op1, op2);
    }

    @Override
    public CustomBooleans evaluer() {
        CustomBooleans cb1 = getOp1().evaluer();
        CustomBooleans cb2 = getOp2().evaluer();
        if (cb1 == CustomBooleans.FAUX || cb2 == CustomBooleans.FAUX) return CustomBooleans.FAUX;
        if (cb1 == CustomBooleans.INCONNU || cb2 == CustomBooleans.INCONNU) return CustomBooleans.INCONNU;
        return CustomBooleans.VRAI;
    }
}
