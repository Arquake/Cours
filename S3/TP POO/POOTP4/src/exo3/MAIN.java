package exo3;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class MAIN {
    public static void main(String[] args) {
        int[] tab = {1 , 13 , 4 , 15 , 32 , 25};
        Predicate<Integer> plusGrandQue10 = t -> t > 10;;
        Predicate<Integer> plusPetitQue20 = t -> t <= 20;

        for ( int i = 0 ; i < tab.length ; i++ ){
            if( plusGrandQue10.test( tab[i] ) ){
                System.out.print(tab[i] + "    ");
            }
        }

        System.out.println();

        for ( int i = 0 ; i < tab.length ; i++ ){
            if( plusGrandQue10.test(tab[i]) && plusPetitQue20.test( tab[i] ) ){
                System.out.print(tab[i] + "    ");
            }
        }
    }
}
