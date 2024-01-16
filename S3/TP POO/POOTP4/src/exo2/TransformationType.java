package exo2;

public class TransformationType {
    public static void main(String[] args) {
        Double[] arrayDouble = {3.0d, 5.0d, 99.9d};
        Transformation transfo = (Object o) -> String.valueOf(o) ;
        EnsTransformable ens = new EnsTransformable <Double>(arrayDouble);
        ens.transformer(transfo);

    }
}
