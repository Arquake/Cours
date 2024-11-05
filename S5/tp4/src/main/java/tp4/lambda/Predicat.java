package tp4.lambda;

@FunctionalInterface
public interface Predicat<T> {
    boolean test(T s);

    default Predicat<T> not() {
        return p -> !this.test(p);
    }

    default Predicat<T> and(Predicat<T> p) {
        return s -> this.test(s) && p.test(s);
    }

    default Predicat<T> or(Predicat<T> p) {
        return s -> this.test(s) || p.test(s);
    }

    default Predicat<T> xor(Predicat<T> p) {
        return s -> this.test(s) != p.test(s);
    }
}
