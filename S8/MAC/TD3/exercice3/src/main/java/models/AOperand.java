package models;

public abstract class AOperand implements IOperation {

    IOperation left;
    IOperation right;

    public AOperand(IOperation left, IOperation right) {
        this.left = left;
        this.right = right;
    }

    public IOperation getLeft() {
        return left;
    }

    public IOperation getRight() {
        return right;
    }
}
