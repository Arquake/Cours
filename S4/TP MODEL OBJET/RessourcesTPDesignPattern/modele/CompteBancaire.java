package modele;

/**
 * Created by utilisateur on 03/04/2019.
 */
public abstract class CompteBancaire implements Comparable<CompteBancaire>{

    private String nom ;
    private String adresse ;
    private int numero ;
    private int solde; //int pour simplifier
    //variable de classe permettant de gérer les numéros auto.
    private static int numeroSuivant =1;

    public CompteBancaire(){
        this.numero=numeroSuivant;
        numeroSuivant++;
    }

    public CompteBancaire(String nom,String adresse){
        this.nom= nom;
        this.adresse=adresse;
        this.solde = 0;
        this.numero=numeroSuivant;
        numeroSuivant ++;
    }
    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public int getNumero(){
        return numero;
    }

    public int getSolde(){
        return solde;
    }

    public void crediter(int montant) {
        if (montant>0) {
            solde = solde + montant;
        }
        else {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
    }
    public void debiter(int montant) throws DebitImpossibleException{
          solde = solde - montant;
          Journalisation.getInstance().ajouterLog("Retrait de " + montant + " euros sur le compte numero " + numero);
    }


    public String toString(){
        return "Compte numéro " + numero + " ouvert au nom de " + nom + "\n Adresse du titulaire " + adresse +"\n Solde actuel " + solde;
    }
    //deux comptes sont égaux s'ils ont même numéro
    public boolean equals(Object autre){
        //tester si les objets sont identiques
        if (this == autre) return true;
        //doit renvoyer false si le paramètre explicite vaut null
        if (autre == null) return false;
        //si les classes ne correspondent pas , elles ne peuvent être égales
        if (getClass() != autre.getClass())
            return false;
        //autre est un objet de type CompteBancaire non null
        CompteBancaire autreCompte =(CompteBancaire)autre;
        //tester si les comptes ont même numéro
        return this.numero== autreCompte.numero;
    }
    public abstract void traitementQuotidien() ;

    public int compareTo( CompteBancaire autreCompte){
        return numero - autreCompte.numero;
    }
}