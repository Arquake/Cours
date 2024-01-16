package exo1;

import static java.lang.String.valueOf;

public class Pile {
    private Maillon sommet;

    public Pile() {
    }

    public void empiler(String s){
        sommet = new Maillon( s, sommet );
    }

    public Object depiler(){
        Object tempMaillon = this.sommet.getValeur();
        this.sommet = this.sommet.getSuivant();
        return (tempMaillon);
    }

    public Maillon getSommet(){
        return (sommet);
    }

    public boolean estVide(){
        return (sommet == null);
    }

    public void consulterContenu(){
        System.out.print("[");
        Maillon tempMaillon = sommet;
        while(tempMaillon.getSuivant()!=null){
            System.out.print(tempMaillon.getValeur()+",");
            tempMaillon = tempMaillon.getSuivant();
        }
        System.out.println(tempMaillon.getValeur()+"]");
    }

    public String pileChain(){
        String chain = "[";
        Maillon tempMaillon = sommet;
        while(tempMaillon.getSuivant()!=null){
            chain += valueOf(tempMaillon.getValeur()) + ",";
            tempMaillon = tempMaillon.getSuivant();
        }
        chain += tempMaillon.getValeur() + "]";
        return (chain);
    }

}
