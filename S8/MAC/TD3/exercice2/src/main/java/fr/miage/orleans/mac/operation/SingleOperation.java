package fr.miage.orleans.mac.operation;

public abstract class SingleOperation implements Operation {

    private Operation op;

    public SingleOperation(Operation op) {
        this.op = op;
    }

    public Operation getOp() {
        return op;
    }
}
