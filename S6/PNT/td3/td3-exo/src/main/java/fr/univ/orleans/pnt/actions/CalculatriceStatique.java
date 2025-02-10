package fr.univ.orleans.pnt.actions;


import org.apache.struts2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class CalculatriceStatique extends ActionSupport {

    private String operand;
    private int operand1;
    private int operand2;
    private int res;
    private List<String> operands;

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

    public java.util.Collection<String> getOperands() {
        if (operands == null) {
            operands = new ArrayList<>();
            operands.add("+");
            operands.add("-");
            operands.add("*");
            operands.add("/");
        }
        return operands;
    }
}
