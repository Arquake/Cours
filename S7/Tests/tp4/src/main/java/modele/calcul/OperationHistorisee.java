package modele.calcul;

public class OperationHistorisee {

    private String operande1;
    private String operande2;
    private String operation;
    private String resultat;

    public OperationHistorisee(String operande1, String operande2, String operation, String resultat) {
        this.operande1 = operande1;
        this.operande2 = operande2;
        this.operation = operation;
        this.resultat = resultat;
    }

    public String getOperande1() {
        return operande1;
    }

    public String getOperande2() {
        return operande2;
    }

    public String getOperation() {
        return operation;
    }

    public String getResultat() {
        return resultat;
    }
}
