package fr.miage.orleans.mac.models;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    List<Corrige> corriges;
    IInformations moyenDePaiement;
    public Panier(){
        this.moyenDePaiement = null;
        this.corriges = new ArrayList<>();
    }
    public void setMoyenDePaiement(IInformations moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
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
    public void pay(){
        int amount = calculerTotal();
        moyenDePaiement.showInformations(amount);

    }
}