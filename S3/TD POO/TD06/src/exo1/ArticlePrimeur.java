package exo1;

import java.time.LocalDate;

public class ArticlePrimeur extends Article {
    private LocalDate datePeremption;
    public ArticlePrimeur(int reference, String libelle, String fournisseur, double prixAchat, double prixArticle, LocalDate datePeremption) {
        super(reference, libelle, fournisseur, prixAchat, prixArticle);
        this.datePeremption = datePeremption;
    }

    @Override
    public String toString(){
        return (super.toString()+"\n"+"date de péremption : "+datePeremption);
    }

    public boolean estPerime(){
        if(datePeremption.compareTo(LocalDate.now()) >= 0){
            return false;
        }
        return true;
    }
}
