package fr.miage.orleans.mac.operation;

public abstract class BiOperation implements Operation {

    private Operation op1;
    private Operation op2;

    public BiOperation(Operation op1, Operation op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    public Operation getOp1() {
        return op1;
    }

    public Operation getOp2() {
        return op2;
    }
}
