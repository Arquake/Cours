package paj.tp3.pajLambdaExo;

import java.util.function.BiFunction;
import java.util.function.Function;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PredicateString nulle = (x) -> x==null;

        PredicateString vide = (x) -> x.isBlank();

        PredicateString nonVide = vide.not();

        PredicateString nonNulle = nulle.not();

        PredicateString nonNulleEtNonVide = nonNulle.and(nonVide);

        System.out.println(nonNulleEtNonVide.test(null));
    }
}