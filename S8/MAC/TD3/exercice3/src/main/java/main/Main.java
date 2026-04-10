package main;

import models.*;
import models.operands.Multiplier;
import models.operands.Plus;

import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        IOperation o = new Plus(new Digit(7), new Multiplier(new Digit(10), new Digit(6)));

        IVisiteur v = new Visiteur();
        o.accept(v);
        System.out.println(v.getValue());
    }
}
