package modele;

/**
 * Created by utilisateur on 03/04/2019.
 */
public class CompteEpargne extends CompteBancaire{

    private double tauxInterets; //taux quotidien des agios
    //constructeur
    public CompteEpargne(String nom,String adresse, double tauxInterets) {
        super (nom, adresse);
        this.tauxInterets = tauxInterets;
    }

    //méthode debiter redéfinie
    public void debiter(int montant)throws DebitImpossibleException {
        if (montant >0) {
            if (getSolde() - montant >= 0)
                super.debiter(montant);
            else {
                throw new DebitImpossibleException("Débit non autorisé : solde <montant");
            }
        }
        else
            throw new IllegalArgumentException("Montant négatif");
    }


    public void traitementQuotidien() {
        crediter ((int) ((double) getSolde() * tauxInterets));

    }
    public String toString(){
        return "Compte Epargne:" + super.toString() ;
    }

}




