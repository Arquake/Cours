public class Main {

    public static void main(String[] args) {
        BiOperation and = (CustomBooleans cb1, CustomBooleans cb2) -> {
            if (cb1 == CustomBooleans.FAUX || cb2 == CustomBooleans.FAUX) return CustomBooleans.FAUX;
            if (cb1 == CustomBooleans.INCONNU || cb2 == CustomBooleans.INCONNU) return CustomBooleans.INCONNU;
            return CustomBooleans.VRAI;
        };

        Operation not = (CustomBooleans cb) -> cb == CustomBooleans.INCONNU? CustomBooleans.INCONNU:cb == CustomBooleans.FAUX? CustomBooleans.VRAI:CustomBooleans.FAUX;

        CustomBooleans c = not.apply(and.apply(CustomBooleans.VRAI, not.apply(CustomBooleans.INCONNU)));
        System.out.println(c);
    }
}
