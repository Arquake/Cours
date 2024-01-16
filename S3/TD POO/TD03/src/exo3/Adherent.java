package exo3;

import java.time.LocalDate;
import java.util.Arrays;

public class Adherent {
    int numAdh;
    String nom;
    Emprunt[] lesEmpruntsEnCours;
    int nbEmpruntsEnCours;

    public Adherent(int numAdh, String nom) {
        this.numAdh = numAdh;
        this.nom = nom;
    }

    public boolean estEnRetard (){
        int i = 0;
        int j = 0;
        while( j < lesEmpruntsEnCours.length && i < nbEmpruntsEnCours){
            if ( lesEmpruntsEnCours[j] != null ){
                i++;
                if ( lesEmpruntsEnCours[j].estEnRetard() ){
                    return true;
                }
            }
            j++;
        }
        return false;
    }

    public boolean empruntPossible (){
        if( estEnRetard() || nbEmpruntsEnCours > 4){ return false; }
        return true;
    }

    public boolean emprunter ( Document d, LocalDate dateEmprunt )
    throws EmpruntImpossibleException{
        if( empruntPossible() ){
            for ( int i = 0 ; i < 5 ; i++ ){
                if ( lesEmpruntsEnCours[i] != null ){
                    lesEmpruntsEnCours[i] = new Emprunt( d, dateEmprunt);
                    nbEmpruntsEnCours++;
                    return true;
                }
            }
        }
        throw new EmpruntImpossibleException();
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "numAdh=" + numAdh +
                ", nom='" + nom + '\'' +
                ", lesEmpruntsEnCours=" + Arrays.toString(lesEmpruntsEnCours) +
                '}';
    }
}
