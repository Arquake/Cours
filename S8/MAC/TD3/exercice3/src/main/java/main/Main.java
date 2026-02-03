package main;

import models.*;

import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        Function<Operand,String> getOperand = operand -> operand == Operand.PLUS? "+":operand==Operand.MINUS? "-":operand==Operand.TIMES? "*":"/";

        TriOperationLogger tol1 = (o1, o2, operand, tol) -> new StringBuilder()
                .append("(")
                .append(o1.getValue(tol))
                .append(" ")
                .append(getOperand.apply(operand))
                .append(" ")
                .append(o2.getValue(tol))
                .append(")")
                .toString();
        ;

        TriOperationLogger tol2 = (o1, o2, operand, tol) -> new StringBuilder()
                .append("(")
                .append(getOperand.apply(operand))
                .append(" ")
                .append(o1.getValue(tol))
                .append(" ")
                .append(o2.getValue(tol))
                .append(")")
                .toString();
        ;

        IOperation o = new Operation(new Operation(new Digit(1), new Digit(2), Operand.PLUS), new Operation(new Digit(4), new Digit(5), Operand.MINUS), Operand.TIMES);
        System.out.println(o.getValue(tol1));
        System.out.println(o.getValue(tol2));
    }
}
