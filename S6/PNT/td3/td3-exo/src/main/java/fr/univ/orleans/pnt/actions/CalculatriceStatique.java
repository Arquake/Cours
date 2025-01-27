package fr.univ.orleans.pnt.actions;

import com.opensymphony.xwork2.ActionSupport;

public class CalculatriceStatique extends ActionSupport {

    private String operand;
    private int operand1;
    private int operand2;
    private int res;

    public String calculate() throws Exception {
        switch (operand) {
            case "+":
                this.res = operand1+operand2;
                break;
            case "-":
                this.res = operand1-operand2;
                break;
            case "*":
                this.res = operand1*operand2;
                break;
            case "/":
                this.res = operand1/operand2;
                break;
            default:
                this.res = 0;
                break;
        }
        return "resultat";
    }

    @Override
    public String execute() throws Exception {
        return "toStatique";
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String oprand) {
        this.operand = oprand;
    }

    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
