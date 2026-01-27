
@FunctionalInterface
public interface Operation {

    public CustomBooleans apply(CustomBooleans b1, CustomBooleans b2) {
        if (b1 == CustomBooleans.FAUX || b2 == CustomBooleans.FAUX) return CustomBooleans.FAUX;
        if (b1 == CustomBooleans.INCONNU || b2 == CustomBooleans.INCONNU) return CustomBooleans.INCONNU;
        return CustomBooleans.VRAI;
    }
}
