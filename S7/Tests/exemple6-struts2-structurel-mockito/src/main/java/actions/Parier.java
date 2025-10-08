package actions;

import facade.exceptions.ResultatImpossibleException;
import modele.Match;
import modele.Pari;

import java.util.ArrayList;
import java.util.List;

public class Parier extends Environnement {
    // variable en entrée
    private long idMatch;
    private double montant;
    private String resultat;

    // variables en sortie
    private Match match;
    private Pari pari;
    private List<String> resultatsPossibles;

    public void setIdMatch(long idMatch) {
        this.idMatch = idMatch;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public long getIdMatch() {
        return idMatch;
    }

    public Match getMatch() {
        return match;
    }

    public Pari getPari() {
        return pari;
    }

    public List<String> getResultatsPossibles() {
        return resultatsPossibles;
    }

    @Override
    public String execute() throws Exception {
        match = getFacade().getMatch(idMatch);
        resultatsPossibles = new ArrayList<>();
        resultatsPossibles.add("nul");
        resultatsPossibles.add(match.getEquipe1());
        resultatsPossibles.add(match.getEquipe2());
        if (montant<=0) {
            addFieldError("montant", "le montant doit être >0.");
            return INPUT;
        }
        if (match==null) {
            addFieldError("idMatch", "match inexistant.");
            return INPUT;
        }
        long idPari = 0L;
        try {
            idPari = getFacade().parier(getUtilisateur().getLogin(), idMatch, resultat, montant);
        } catch (ResultatImpossibleException e) {
            addFieldError("resultat", "résultat incohérent.");
            return INPUT;
        }
        pari = getFacade().getPari(idPari);
        match = pari.getMatch();
        return SUCCESS;
    }

}
