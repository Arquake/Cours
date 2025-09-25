package modele.constructionOperande;

public class ConstructionOperandePartieEntiere implements EtatConstructionOperande {
    private double operandeCourante;

    public ConstructionOperandePartieEntiere() {
        operandeCourante = 0d;
    }

    @Override
    public EtatConstructionOperande inputDigit(int x) {
        this.operandeCourante = this.operandeCourante*10+x;
        return this;
    }

    @Override
    public EtatConstructionOperande inputDot() {
        return new ConstructionOperandePartieDecimale(operandeCourante);
    }

    @Override
    public double getOperandeCourante() {
        return operandeCourante;
    }


    @Override
    public String getOperandeCouranteString() {
        return Integer.toString((int)operandeCourante);
    }

    @Override
    public EtatConstructionOperande reset() {
        this.operandeCourante = 0d;
        return this;
    }


}
