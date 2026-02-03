package models;

public class Operation implements IOperation {

    IOperation value1;
    IOperation value2;
    Operand operand;

    private Operation(){}

    public Operation(IOperation value1, IOperation value2, Operand operand) {
        this.value1 = value1;
        this.value2 = value2;
        this.operand = operand;
    }

    @Override
    public String getValue(TriOperationLogger tol) {
        return tol.apply(value1, value2, operand, tol);
    }
}
