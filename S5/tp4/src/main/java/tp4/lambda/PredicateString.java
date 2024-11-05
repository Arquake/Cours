package tp4.lambda;

import java.util.function.Predicate;

@FunctionalInterface
public interface PredicateString {
    boolean test(String s);

    default PredicateString not() {
        return p -> !this.test(p);
    }

    default PredicateString and(PredicateString pn) {
        return s -> this.test(s) && pn.test(s);
    }
}
