package exo3;

public class Personnel {
    Employe[] personnel = new Employe[1000];

    public Employe rechercherEmploye(int matricule){
        for(int i = 0 ; i < personnel.length ; i++){
            if( personnel[i] != null && personnel[i].getId() == matricule ){ return personnel[i]; }
        }
        return null ;
    }

    public boolean ajouterEmploye(Employe e){
        if( rechercherEmploye(e.getId()) == null ){
            for(int i = 0 ; i < personnel.length ; i++){
                if( personnel[i] == null ){
                    personnel[i] = e;
                    return true;
                }
            }
            Employe[] tempEmploy = new Employe[personnel.length * 2];
            for(int i = 0 ; i < personnel.length ; i++){
                tempEmploy[i] = personnel[i];
            }
            tempEmploy[personnel.length] = e ;
            personnel = tempEmploy;
            return true;
        }
        return false;
    }

    public double montantSalaireBrutsMensuels(){
        int somme = 0;
        for( int i = 0 ; i < personnel.length ; i++ ){
            if(personnel[i] != null) {
                somme += personnel[i].calculerSalaireBrutMensuel();
            }
        }
        return somme;
    }

    @Override
    public String toString() {
        String listeEtatEmployes ="";
        for( int i = 0 ; i < personnel.length ; i++ ){
            if(personnel[i] != null) {
                listeEtatEmployes += personnel[i].toString()+"\n";
            }
        }
        return listeEtatEmployes;
    }
}
