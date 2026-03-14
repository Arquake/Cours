package programme.models;

public class Racine extends Operation {

    public Racine(Operation successor) {
        super(
                successor,
                Operations.RACINE,
                values -> values.length == 1,
                values -> Math.sqrt(values[0])
        );
    }
}
