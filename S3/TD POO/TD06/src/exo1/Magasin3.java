package exo1;

import java.util.*;

public class Magasin3 {
    HashMap<String, Article> lesArticles;

    public Magasin3() {
        this.lesArticles = new HashMap<String, Article>();
    }

    public void ajouterArticle(String r, Article a){
        lesArticles.put(r, a);
    }

    @Override
    public String toString() {
        String chain = "";
        for( String key : lesArticles.keySet() ){
            chain += key + " : " + lesArticles.get(key) + "\n";
        }
        return chain;
    }
}
