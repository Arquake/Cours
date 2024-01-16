package exo1;

public class Magasin {
    private Article[] lesArticles;
    private int capacity = 1000;
    private int nb = 0;

    public Magasin() {
        this.lesArticles = new Article[capacity];
    }

    public boolean estPresent (Article a){
        for( int i = 0 ; i < nb ; i++ ){ if( a.equals(lesArticles[i])){ return true; } }
        return false;
    }

    public boolean ajouterArticle(Article a){
        if( nb + 1 < capacity && !estPresent( a )){
            lesArticles[ nb ] = a;
            nb++;
            return true;
        }
        return false;
    }

    public boolean promotion(Article a, int pourcentage){
        for( int i = 0 ; i < nb ; i++ ){
            if( a.equals(lesArticles[i])){
                lesArticles[i].promotion(pourcentage);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String chain = "";
        for( int i = 0 ; i < nb ; i++ ){
            chain += lesArticles[i].toString() + "\n";
        }
        return chain;
    }

    public void solder (int pourcentage, Soldable...lesArticlesASolder){
        for( int i = 0; i < lesArticlesASolder.length ; i++ ) {
            lesArticlesASolder[i].solder( pourcentage );
        }
    }
}
