package modele.calcul;

import modele.exceptions.NonSupporteeException;
import modele.exceptions.OperationMalFormeeException;
import modele.operations.Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class EtatCalcul {
    private Operations operations;
    private Optional<Double> operandeCourante;
    private List<OperationHistorisee> historique;

    public EtatCalcul(Operations operations) {

        this.operations = operations;
        this.historique = new ArrayList<>();
    }

    public Operations getOperations() {
        return operations;
    }

    public void setHistorique(List<OperationHistorisee> historique) {
        this.historique = historique;
    }

    public Optional<Double> getOperandeCourante(){
        return operandeCourante;
    }
    public double getOperandeCouranteValue() throws OperationMalFormeeException {
        if (this.operandeCourante.isPresent()) {
            return this.operandeCourante.get();
        }
        else {
            throw new OperationMalFormeeException();
        }
    }

    public void setOperandeCourante(double operande){
        this.operandeCourante= Optional.of(operande);
    }

    public double getResultat() throws OperationMalFormeeException {
        if (operandeCourante.isPresent())
            return operandeCourante.get();
        else
            throw new OperationMalFormeeException();
    }

    public List<OperationHistorisee> getHistorique() {
        return historique;
    }

    public abstract EtatCalcul calculCourant(String operation) throws NonSupporteeException, OperationMalFormeeException;

}
