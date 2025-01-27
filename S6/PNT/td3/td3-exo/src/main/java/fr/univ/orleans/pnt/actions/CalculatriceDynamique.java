package fr.univ.orleans.pnt.actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.CalculatriceDynamiqueDuFuturImpl;

public class CalculatriceDynamique extends ActionSupport {

    private static CalculatriceDynamiqueDuFuturImpl calculatrice;

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

        return "resultat";
    }

    public static CalculatriceDynamiqueDuFuturImpl getCalculatrice() {
        return calculatrice;
    }
}
