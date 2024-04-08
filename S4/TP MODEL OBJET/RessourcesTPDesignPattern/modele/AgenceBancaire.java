package modele;

import modele.Exception.MontantInvalidException;
import modele.Exception.NumeroCompteInvalidException;
import modele.Exception.OperationInvalidException;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by utilisateur on 03/04/2019.
 */
public class AgenceBancaire {
    private Map<Integer,CompteBancaire> lesComptes;

    /** Creates a new instance of AgenceBancaire */
    public AgenceBancaire() {
        lesComptes = new TreeMap<>();
        CompteBancaire c1 = new CompteCourant("CC1","a1",0.1);
        CompteBancaire c2 = new CompteCourant("CC2","a2",0.1);
        CompteBancaire c3 = new CompteCourant("CC3","a3",0.2);
        CompteBancaire c4 = new CompteEpargne("CE1","a1",0.1);
        CompteBancaire c5 = new CompteEpargne("CE2","a4",0.1);
        lesComptes.put(c1.getNumero(),c1);
        lesComptes.put(c2.getNumero(),c2);
        lesComptes.put(c3.getNumero(),c3);
        lesComptes.put(c4.getNumero(),c4);
        lesComptes.put(c5.getNumero(),c5);
    }

    //ajouter un nouveau compte
    public void ajouterCompte(CompteBancaire c){
        lesComptes.put (c.getNumero(),c);
    }



    public String toString(){
        return lesComptes.values().toString();
    }

    public void supprimerCompte(CompteBancaire c) {
        lesComptes.remove(c.getNumero());

    }

    public void traitementQuotidien(){
        Collection<CompteBancaire> comptes = lesComptes.values();
       for (CompteBancaire c : comptes){
               c.traitementQuotidien();

       }
    }

    public void gererOperation(int numeroCompte, String type, int montant) throws NumeroCompteInvalidException, OperationInvalidException, MontantInvalidException {
        if (lesComptes.get(numeroCompte) == null){throw new NumeroCompteInvalidException();}
        if (!type.equalsIgnoreCase("D") && !type.equalsIgnoreCase("C")){throw new OperationInvalidException();}
        try {
            if (type.equalsIgnoreCase("D")) {
                lesComptes.get(numeroCompte).debiter(montant);
            } else {
                lesComptes.get(numeroCompte).crediter(montant);
            };
        } catch (Exception e) {
            throw new MontantInvalidException();
        }
    }
}
