package fr.miage.orleans.mac.models;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    List<Corrige> corriges;
    public Panier(){
        this.corriges = new ArrayList<>();
    }
    public void addItem(Corrige corrige){
        this.corriges.add(corrige);
    }
    public void removeItem(Corrige corrige){
        this.corriges.remove(corrige);
    }
    public int calculerTotal(){
        int sum = 0;
        for(Corrige corrige : corriges){
            sum += corrige.getPrice();
        }
        return sum;
    }
    public void pay(String nom, String noCarte, String crypto,
                    String expire){
        int amount = calculerTotal();
        System.out.println("Paiement de " + amount + "€ par " + nom + "CB " + noCarte + "/" + crypto + " expire:" + expire);
    }

    public void payByPaypal(String email, String numeroUtilisation){
        int amount = calculerTotal();
        System.out.println("Paiement de " + amount + "€ sur " + email + " avec le code " + numeroUtilisation);
    }
}