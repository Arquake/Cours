package fr.univ.orleans.pnt.actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.CalculatriceDynamiqueDuFuturImpl;

public class CalculatriceDynamique extends ActionSupport {

    private static CalculatriceDynamiqueDuFuturImpl calculatrice;

    private double operand1;
    private double operand2;
    private String operand;
    private double res;

    public CalculatriceDynamique() {
        if (calculatrice == null) {
            calculatrice = new CalculatriceDynamiqueDuFuturImpl();
        }
    }

    @Override
    public String execute() throws Exception {
        return "toDynamique";
    }

    public String calculate() throws Exception {
        res = calculatrice.doCalcul(operand,operand1, operand2);
        return "resultat";
    }

    public long getIterations() {
        return calculatrice.getValeurCompteur();
    }

    public java.util.Collection<String> getOperations() {
        return calculatrice.getOperations();
    }

    public double getRes() {
        return res;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }
}
