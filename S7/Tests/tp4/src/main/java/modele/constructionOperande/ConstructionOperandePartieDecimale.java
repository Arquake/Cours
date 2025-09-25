package modele.constructionOperande;

public class ConstructionOperandePartieDecimale implements EtatConstructionOperande {
    private double operandeCourante;
    private int puissanceNeg;
    public ConstructionOperandePartieDecimale(double operandeCourante) {

        this.operandeCourante = operandeCourante;
        this.puissanceNeg = -1;
    }


    @Override
    public EtatConstructionOperande inputDigit(int x) {

        this.operandeCourante = this.operandeCourante+x*Math.pow(10,puissanceNeg);
        puissanceNeg--;
        return this;

    }

    @Override
    public EtatConstructionOperande inputDot() {
        throw new NumberFormatException();
    }

    @Override
    public double getOperandeCourante() {
        return operandeCourante;
    }

    @Override
    public String getOperandeCouranteString() {
        return Double.toString(getOperandeCourante());
    }

    @Override
    public EtatConstructionOperande reset() {
        return new ConstructionOperandePartieEntiere();
    }
}
