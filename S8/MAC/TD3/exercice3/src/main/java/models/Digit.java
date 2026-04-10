package models;

public class Digit implements IOperation {

    private int value;

    public Digit(int value) {
        this.value = value;
    }

    @Override
    public void accept(IVisiteur v) {
        v.visiterDigit(this);
    }

    public int getValue() {
        return value;
    }
}
