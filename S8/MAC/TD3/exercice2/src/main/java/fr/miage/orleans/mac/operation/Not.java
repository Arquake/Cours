package fr.miage.orleans.mac.operation;

import fr.miage.orleans.mac.CustomBooleans;

public class Not extends SingleOperation {

    public Not(Operation op) {
        super(op);
    }

    @Override
    public CustomBooleans evaluer() {
        CustomBooleans cb = getOp().evaluer();
        return cb == CustomBooleans.INCONNU? CustomBooleans.INCONNU:cb == CustomBooleans.FAUX? CustomBooleans.VRAI:CustomBooleans.FAUX;
    }
}
