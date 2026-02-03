package models;

public class Digit implements IOperation {

    int value;

    private  Digit() {}
    public Digit(int value) {
        this.value = value;
    }

    @Override
    public String getValue(TriOperationLogger tol) {
        return String.valueOf(value);
    }
}
