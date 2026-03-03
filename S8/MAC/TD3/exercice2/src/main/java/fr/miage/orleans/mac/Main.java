package fr.miage.orleans.mac;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        BiFunction<CustomBooleans, CustomBooleans, CustomBooleans> et = (b1, b2) -> {
            if (b1 == CustomBooleans.FAUX || b2 == CustomBooleans.FAUX) return CustomBooleans.FAUX;
            if (b1 == CustomBooleans.INCONNU || b2 == CustomBooleans.INCONNU) return CustomBooleans.INCONNU;
            return CustomBooleans.VRAI;
        };

        Function<CustomBooleans, CustomBooleans> not = b -> {
            if (b == CustomBooleans.INCONNU) return CustomBooleans.INCONNU;
            if (b == CustomBooleans.FAUX) return CustomBooleans.VRAI;
            return CustomBooleans.FAUX;
        };

        System.out.println(not.apply(et.apply(CustomBooleans.VRAI, not.apply(CustomBooleans.INCONNU))));

    }
}
