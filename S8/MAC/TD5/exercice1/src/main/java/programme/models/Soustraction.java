package programme.models;

public class Soustraction extends Operation {

    public Soustraction(Operation successor) {
        super(
                successor,
                Operations.SOUSTRACTION,
                values -> values.length == 2,
                values -> values[0] - values[1]
        );
    }
}
