package exo1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Magasin{
    private List<Article> lesArticles;
    private int capacity = 1000;

    public Magasin() { }

    public boolean ajouterArticle(Article a){
        if( lesArticles.size() + 1 < capacity ){
            if ( rechercherArticle(a.getReference()) == null) {
                lesArticles.add(a);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String chain = "";
        for( int i = 0 ; i < lesArticles.size() ; i++ ){
            chain += lesArticles.get(i).toString() + "\n";
        }
        return chain;
    }

    public void solder (int pourcentage, Soldable...lesArticlesASolder){
        for( int i = 0; i < lesArticlesASolder.length ; i++ ) {
            lesArticlesASolder[i].solder( pourcentage );
        }
    }

    public Article rechercherArticle(int reference){
        for ( Article art : lesArticles ){
            if (art.getReference() == reference) { return art; }
        }
        return null;
    }

    public boolean supprimerArticle (int ref ){
        Iterator<Article> itr = lesArticles.iterator();
        int i = 0;
        while(itr.hasNext()){
            if( itr.next().getReference() == ref ){
                lesArticles.remove(i);
                return true;
            }
            i++;
        }
        //for( int i = 0 ; i < lesArticles.size() ; i++ ){
        //    if( lesArticles.get(i).getReference() == ref ){
        //        lesArticles.remove(i);
        //        return true;
        //    }
        //}
        return false;
    }

    public boolean promotion(int ref, int pourcentage){
        Article art = rechercherArticle(ref);
        if ( art != null) {
            art.promotion(pourcentage);
            return true;
        }
        return false;
    }

    public void liquidationTotale(int pourcentage){
        for( int i = 0 ; i < lesArticles.size() ; i++ ){
            lesArticles.get(i).promotion(pourcentage);
        }
    }

    public List<Article> triParOrdreCroissant(){
        List<Article> liste= new ArrayList<>(lesArticles);
        liste.sort( (Article a1, Article a2) -> { return (int) (a1.getPrixVente() - a2.getPrixVente()); });
        return liste;
    }
}
