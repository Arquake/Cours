package tp4.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*
        Predicat<String> nulle = s -> s == null;
        Predicat<String> vide = s -> s.isBlank();

        Predicat<String> nonNulle = s -> nulle.not().test(s);
        Predicat<String> nonVide = s -> vide.not().test(s);

        Predicat<String> nonNulleEtNonVide = nonNulle.and(nonVide);

        Predicat<Personne> commenceParJ = s -> s.firstLetterName()=='J';
        Predicat<Personne> fourLetterMinPersonne = s -> s.prenomLength() == 4;

        Predicat<Personne> both = commenceParJ.or(fourLetterMinPersonne);

        Personne t = new Personne("Bose", "test");
        System.out.println();*/

        Function<Integer, Long> t = addFunction(78);
        int[] li = new int[]{1,4,7,5};
        for (int j : li) {
            System.out.println(t.apply(j));
        }
    }

    static Function<Integer, Long> addFunction(int n) {
        return l -> (long) (n + l);
    }
}