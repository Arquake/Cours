package tp4.lambda;

import java.util.function.Function;

@FunctionalInterface
public interface ValideCompletSimpson {
    void estValide(Simpson s);

    static ValideCompletSimpson verifie(Predicat<Simpson> f, String msg) throws RuntimeException {
        return s -> {
            if (!f.test(s)) { throw new RuntimeException(msg);}
        };
    }

    default ValideCompletSimpson puisVerifie(Function<Simpson, Boolean> f, String msg) throws RuntimeException {
        return s -> {
            this.estValide(s);
            if (!f.apply(s)) { throw new RuntimeException(msg);}
        };
    }
}
