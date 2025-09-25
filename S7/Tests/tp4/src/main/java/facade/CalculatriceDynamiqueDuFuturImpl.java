package facade;

import modele.calcul.EtatCalcul;
import modele.calcul.EtatInitialCalcul;
import modele.calcul.OperationHistorisee;
import modele.constructionOperande.ConstructionOperandePartieEntiere;
import modele.constructionOperande.EtatConstructionOperande;
import modele.exceptions.NonSupporteeException;
import modele.exceptions.OperationMalFormeeException;
import modele.operations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatriceDynamiqueDuFuturImpl implements CalculatriceDynamiqueDuFutur {


    private final Operations operationsGerees = new Multiplication(new Soustraction(new Division(new Somme(null))));

    private EtatConstructionOperande etatConstructionOperande;
    private EtatCalcul etatCalcul;



    private static Map<String,String> traduction = new HashMap<>();
    public static Map<String,String> reverseTraduction = new HashMap<>();
    static {
        traduction.put("/","Division");
        traduction.put("+","Somme");
        traduction.put("-","Soustraction");
        traduction.put("*","Multiplication");
        traduction.put("=","=");

        reverseTraduction.put("Division","/");
        reverseTraduction.put("Somme","+");
        reverseTraduction.put("Soustraction","-");
        reverseTraduction.put("Multiplication","*");

    }


    public CalculatriceDynamiqueDuFuturImpl() {
        this.etatConstructionOperande = new ConstructionOperandePartieEntiere();
        this.etatCalcul = new EtatInitialCalcul(operationsGerees);

    }

    @Override
    public double getResultatCourant() throws OperationMalFormeeException {
        return etatCalcul.getResultat();
    }

    @Override
    public void inputDigit(int digit) {
        this.etatConstructionOperande = this.etatConstructionOperande.inputDigit(digit);
    }

    @Override
    public void inputOperation(String op) throws OperationMalFormeeException, NonSupporteeException {
        double operande = this.etatConstructionOperande.getOperandeCourante();
        this.etatConstructionOperande = this.etatConstructionOperande.reset();
        this.etatCalcul.setOperandeCourante(operande);
        this.etatCalcul = this.etatCalcul.calculCourant(traduction.get(op));

    }

    @Override
    public void inputDot() {
        this.etatConstructionOperande = this.etatConstructionOperande.inputDot();

    }

    @Override
    public void inputEqual() throws OperationMalFormeeException, NonSupporteeException {

        this.etatCalcul.setOperandeCourante(this.etatConstructionOperande.getOperandeCourante());
        this.etatConstructionOperande = this.etatConstructionOperande.reset();
        this.etatCalcul = this.etatCalcul.calculCourant(traduction.get("="));

    }

    @Override
    public String getOperandeCourante() {
        return this.etatConstructionOperande.getOperandeCouranteString();
    }

    @Override
    public List<OperationHistorisee> getHistorique() {
        return this.etatCalcul.getHistorique();
    }

}
