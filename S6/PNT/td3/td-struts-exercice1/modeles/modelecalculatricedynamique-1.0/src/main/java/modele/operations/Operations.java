package modele.operations;

import modele.exceptions.NonSupporteeException;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Operations {

    private String nomOperation;
    private Operations next;

    public Operations(String nomOperation, Operations next) {
        this.nomOperation = nomOperation;
        this.next = next;
    }

    protected abstract double getResultat(double operande1, double operande2);

    public  double getResultat(String operation, double operande1, double operande2) throws NonSupporteeException {
        if (this.nomOperation.equals(operation)) {
            return this.getResultat(operande1,operande2);
        }
        else {
            if (this.next==null) {
                throw new NonSupporteeException();
            }
            else {
                return this.next.getResultat(operation,operande1,operande2);
            }
        }
    }

    public Collection<String> getOperations() {
        Collection<String> others;
        if (next == null) {
            others= new ArrayList<String>();
        }
        else {
            others = next.getOperations();
        }
        others.add(nomOperation);
        return others;
    }



}
