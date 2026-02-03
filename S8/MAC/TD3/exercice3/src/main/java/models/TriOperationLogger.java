package models;

@FunctionalInterface
public interface TriOperationLogger {

    String apply(IOperation value1, IOperation value2, Operand operand, TriOperationLogger tol);
}
