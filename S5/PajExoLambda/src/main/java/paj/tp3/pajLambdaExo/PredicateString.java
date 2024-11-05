package paj.tp3.pajLambdaExo;

import java.util.function.Function;

@FunctionalInterface
public interface PredicateString {
    public boolean test(String chain);

    default PredicateString not(){
        return s -> !this.test(s);
    }

    default PredicateString and(PredicateString s2) {
        return s -> this.test(s) && s2.test(s);
    }
}
