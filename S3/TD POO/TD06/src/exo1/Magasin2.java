package exo1;

import com.sun.source.tree.Tree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Spliterators.iterator;

public class Magasin2{
    private SortedSet<Article> lesArticles = new TreeSet<Article>(
        new Comparator<Article>(){
            @Override
            public int compare(Article o1,Article o2){
                return o1.getLibelle().compareTo(o2.getLibelle());
            }
    });

    private int capacity = 1000;

    public Magasin2() { }

    public boolean ajouterArticle(Article a){
        if( lesArticles.size() + 1 < capacity ){
            if ( a != null && lesArticles.size() < capacity) {
                lesArticles.add(a);
                return true;
            }
        }
        return false;
    }

    /*
    Method Libelle
     */

    //public boolean ajouterArticle(Article a){
    //    if( lesArticles.size() + 1 < capacity ){
    //        if ( rechercherArticle(a.getReference()) == null) {
    //            for( int i = 0; i < lesArticles.size(); i++){
    //                if( a.getLibelle().compareTo(lesArticles.get(i).getLibelle()) < 0 ){
    //                    lesArticles.add( i, a );
    //                    return true;
    //                }
    //            }
    //        }
    //    }
    //    return false;
    //}

    @Override
    public String toString(){
        String chain = "";
        Iterator itr = lesArticles.iterator();
        while( itr.hasNext() ){
            chain += itr.next().toString()+"\n";
        }
        return chain;
    }

    public Article lePremierArticle(){
        return lesArticles.first();
    }

    public Article leDernierArticle(){
        return lesArticles.last();
    }


}
