package exo1;

public class ArticleHabillement extends Article implements Soldable{
    private int taille;
    public ArticleHabillement(int reference, String libelle, String fournisseur, double prixAchat, double prixArticle, int taille) {
        super(reference, libelle, fournisseur, prixAchat, prixArticle);
        this.taille = taille;
    }

    @Override
    public String toString(){
        return ("taille : "+taille +" "+super.toString());
    }


    public void solder(int pourcentage) {
        double vente = getPrixVente();
        vente=vente-vente*pourcentage/100.0;
        super.promotion((int) ((getPrixVente()/getPrixArticle())*100));
    }

}
