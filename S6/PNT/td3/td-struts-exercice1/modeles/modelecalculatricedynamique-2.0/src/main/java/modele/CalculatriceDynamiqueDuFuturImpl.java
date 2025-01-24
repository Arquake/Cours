package modele;

import modele.exceptions.NonSupporteeException;
import modele.operations.Division;
import modele.operations.Operations;
import modele.operations.Pythagore;
import modele.operations.Somme;

import java.util.Collection;

public class CalculatriceDynamiqueDuFuturImpl implements CalculatriceDynamiqueDuFutur {


    private final Operations operationsGerees = new Division(new Somme(new Pythagore(null)));


    private long compteur = 0;


    public Collection<String> getOperations() {
        return this.operationsGerees.getOperations();
    }

    public long getValeurCompteur() {
        return compteur;
    }

    public double doCalcul(String operation, double operande1, double operande2) throws NonSupporteeException {
        compteur++;
        return this.operationsGerees.getResultat(operation, operande1,operande2);

    }
}
