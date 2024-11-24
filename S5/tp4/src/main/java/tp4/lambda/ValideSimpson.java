package tp4.lambda;

@FunctionalInterface
public interface ValideSimpson {
    void estValide(Simpson s) throws RuntimeException;
}
