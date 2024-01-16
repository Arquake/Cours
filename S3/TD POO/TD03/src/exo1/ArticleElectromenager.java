package exo1;

public class ArticleElectromenager extends Article implements Soldable{
    private int poids;
    public ArticleElectromenager(int reference, String libelle, String fournisseur, double prixAchat, double prixArticle, int poids) {
        super(reference, libelle, fournisseur, prixAchat, prixArticle);
        this.poids = poids;
    }

    @Override
    public String toString(){
        return ("poids (en grammes) : "+poids +" "+super.toString());
    }

    public void solder(int pourcentage) {
        super.promotion(pourcentage);
    }
}
