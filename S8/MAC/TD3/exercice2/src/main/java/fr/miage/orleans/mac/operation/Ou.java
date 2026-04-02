package fr.miage.orleans.mac.operation;

import fr.miage.orleans.mac.CustomBooleans;

public class Ou extends BiOperation {

    public Ou(Operation op1, Operation op2) {
        super(op1, op2);
    }
    @Override
    public CustomBooleans evaluer() {
        Operation o = new Not(new Et(new Not(getOp1()), new Not(getOp2())));
        return o.evaluer();
    }
}
