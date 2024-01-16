package exo5;

public class Pile {
    private Maillon sommet;

    public Pile() {
    }

    public void empiler(String s){
        sommet = new Maillon( s, sommet );
    }

    public String depiler(){
        String tempMaillon = this.sommet.getValeur();
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
}
