package modele;

/**
 * Created by utilisateur on 03/04/2019.
 */
public class CompteCourant extends CompteBancaire{

    private double tauxAgios; //taux quotidien des agios
    //constructeur
    public CompteCourant(String nom,String adresse, double tauxAgios) {
        super (nom, adresse);

        this.tauxAgios = tauxAgios;
    }
    public CompteCourant() {
        super ();

    }
	public void debiter(int montant) throws DebitImpossibleException{
        if (montant >0) {
           super.debiter(montant);
        }
        else
            throw new IllegalArgumentException("Le montant doit être positif");
    }
	
    public void traitementQuotidien()  {
        if (this.getSolde() < 0) {
            try {
                debiter ((int) (-1.0 * (double) getSolde() * tauxAgios));
            } catch (DebitImpossibleException e) {
                e.printStackTrace();
            }
        }
    }
    public String toString(){
        return "Compte Courant :" + super.toString() ;
    }


}

