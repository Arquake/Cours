package exo1;

import java.util.*;

public class Annuaire {
    HashMap<Personne, ListeNumTel> listeAnnuaire;

    public Annuaire() {
        listeAnnuaire = new HashMap<Personne, ListeNumTel>();
    }

    boolean ajouterEntree(Personne p, ListeNumTel nums){
        if( listeAnnuaire.containsKey( p ) ){ return false; }
        listeAnnuaire.put(p, nums);
        return true;
    }

    ListeNumTel numeros(Personne p){
        if( listeAnnuaire.containsKey( p ) ){ return null; }
        return listeAnnuaire.get( p );
    }

    java.util.Iterator <Personne> personne(){
        return listeAnnuaire.keySet().iterator();
    }

    void ajouterNumeroFin(Personne p, NumTel n){
        if( !listeAnnuaire.containsKey( p ) ){ ajouterEntree( p, new ListeNumTel(n) ); }
        listeAnnuaire.get( p ).ajouterFin( n );
    }

    public NumTel premierNumero (Personne p){
        if( !listeAnnuaire.containsKey( p ) ){ return null; }
        return listeAnnuaire.get( p ).numero( 0 );
    }

    public void supprimer (Personne p){
        if( listeAnnuaire.containsKey( p ) ){ listeAnnuaire.remove( p ); }
    }

    public void supprimerNumero (int n, Personne p){
        if( listeAnnuaire.containsKey( p ) ){
            listeAnnuaire.get( p ).retirer( n );
            if( listeAnnuaire.get( p ).nbNumeros() == 0 ){
                listeAnnuaire.remove( p );
            }
        }
    }

    public Set<Personne> lesEntreesPour (String nom){
        Set<Personne> pers = new HashSet<Personne>();
        for (Personne personne : listeAnnuaire.keySet()) {
            if( personne.getNom_().equals(nom) ){ pers.add( personne ); }
        }
        return pers;
    }

    public String toString (){
        String sequence = "";
        for (Personne personne : listeAnnuaire.keySet()) {
            sequence += personne.toString() + " : " + listeAnnuaire.get( personne ).toString() + "\n";
        }
        return sequence;
    }
}


/*

3. HashMap

4.

 */
