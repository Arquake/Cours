package programme.models;

public class Cosinus extends Operation {

    public Cosinus(Operation successor) {
        super(
                successor,
                Operations.COSINUS,
                values -> values.length == 1,
                values -> Math.cos(values[0])
        );
    }
}
