package exo2;

import java.util.Arrays;

public class ArticlesTest {

    Article[] listeArticles = new Article[1];

    void ajouterArticle( Article a ){
        Article[] newList = new Article[listeArticles.length+1];
        for( int i = 0 ; i < listeArticles.length ; i++ ){
            newList[i] = listeArticles[i];
        }
        newList[listeArticles.length] = a;
        listeArticles = newList;
    }

    @Override
    public String toString(){
        String sequence = "";
        Article[] arr = listeArticles.clone();
        Arrays.sort(listeArticles);
        for( Article a : listeArticles ){
            if( a == null ){ break; }
            sequence += a.toString() + "\n";
        }
        listeArticles = arr;
        return sequence;
    }
}

/*

générer un constructeur pour incrémenter les numéros d'article

 */
