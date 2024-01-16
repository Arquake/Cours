package exo1;

import java.util.Comparator;

public class Article implements Comparable<Article> {
    private int reference;
    private String libelle,fournisseur;
    private double prixAchat, prixArticle, prixVente;

    public Article(int reference, String libelle, String fournisseur, double prixAchat, double prixArticle) {
        this.reference = reference;
        this.libelle = libelle;
        this.fournisseur = fournisseur;
        this.prixAchat = prixAchat;
        this.prixArticle = prixArticle;
        this.prixVente = prixAchat;
    }
    public void promotion (int pourcentage){
        prixVente=prixAchat-prixAchat*pourcentage/100.0;
    }
    public void remonterPrix (){
        prixVente=prixAchat;
    }

    @Override
    public String toString(){
        return ( "ref : "+reference+" libelle : "+libelle+" fournisseur : "+fournisseur+" prix d'achat : "+prixAchat+" prix de l'article : "+prixArticle+" prix vente : "+prixVente );
    }

    @Override
    public boolean equals(Object a){
        if( a == this) { return true; }
        if( a == null ) { return false; }
        if( a.getClass() != this.getClass() ){ return false; }
        Article cloneA = (Article)a;
        if( this.reference == cloneA.reference && this.libelle == cloneA.libelle && this.fournisseur == cloneA.fournisseur && this.prixAchat == cloneA.prixAchat && this.prixArticle == cloneA.prixArticle && this.prixVente == cloneA.prixVente ){ return true; }
        return false;
    }

    public double getPrixArticle() {
        return prixArticle;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public int getReference() {
        return reference;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public int compareTo(Article o) {
        if( o == null ) { return -1;}
        if( this == o ){ return 0; }
        if( this.getPrixVente() < o.getPrixVente()){
            return -1;
        } else if ( this.getPrixVente() > o.getPrixVente() ){
            return 1;
        }
        return 0;
    }
    public int compare(Article o1, Article o2)
    {
        if( o1.getPrixVente() < o2.getPrixVente()){
            return -1;
        } else if ( o1.getPrixVente() > o2.getPrixVente() ){
            return 1;
        }
        return 0;
    }
}
