import fr.miage.orleans.mac.operation.Operation;
import fr.miage.orleans.mac.operation.Et;
import fr.miage.orleans.mac.operation.Ou;
import fr.miage.orleans.mac.values.Inconnu;
import fr.miage.orleans.mac.values.Vrai;

public class Main {

    public static void main(String[] args) {
        Operation op = new Et(new Inconnu(), new Ou(new Vrai(), new Inconnu()));

        System.out.println(op.evaluer());
    }
}
