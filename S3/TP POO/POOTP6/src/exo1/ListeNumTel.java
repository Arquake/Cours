package exo1;

import java.util.Arrays;

public class ListeNumTel {
    NumTel[] listeNumeros;

    public ListeNumTel(NumTel numero) {
        this.listeNumeros = new NumTel[1];
        this.listeNumeros[0] = numero;
    }

    boolean ajouter(int index, NumTel num){
        if ( contientNumero( num.getNumero() ) ){ return false;}
        NumTel[] nouvelleListeNumeros = new NumTel[ listeNumeros.length+1 ];
        int i = 0;
        int j = 0;
        while ( i < nouvelleListeNumeros.length ){
            if ( i != index ){ nouvelleListeNumeros[i+j] = listeNumeros[i]; }
            else { nouvelleListeNumeros[i] = num; j++; }
        }
        return true;
    }

    boolean ajouterFin(NumTel num){
        if ( contientNumero( num.getNumero() ) ){ return false;}
        NumTel[] nouvelleListeNumeros = new NumTel[ listeNumeros.length+1 ];
        for ( int i = 0 ; i < listeNumeros.length ; i++ ){
            nouvelleListeNumeros[i] = listeNumeros[i];
        }
        nouvelleListeNumeros[nouvelleListeNumeros.length-1] = num;
        listeNumeros = nouvelleListeNumeros;
        return true;
    }

    boolean contientNumero(int num){
        for (int i = 0 ; i < listeNumeros.length ; i++ ){
            if ( listeNumeros[i].getNumero() == num ){ return true; }
        }
        return false;
    }

    public java.util.Iterator<NumTel> iterator(){
        return Arrays.stream(listeNumeros).iterator();
    }

    int nbNumeros(){
        return listeNumeros.length;
    }

    NumTel numero(int index){
        if ( listeNumeros.length <= index ){ return null; }
        if ( listeNumeros[index] == null ){ return null; }
        return listeNumeros[index];
    }

    boolean retirer(int num) {
        int i = 0;
        if (!contientNumero(num) || listeNumeros.length < 2 ) {
            return false;
        }
        while (i < listeNumeros.length) {
            if (listeNumeros[i].getNumero() == num) {
                break;
            }
            i++;
        }
        NumTel[] nouvelleListeNumeros = new NumTel[listeNumeros.length - 1];
        for (int j = 0; j < i; j++){
            nouvelleListeNumeros[j] = listeNumeros[j];
        }
        for (int j = i + 1; j < listeNumeros.length; j++){
            nouvelleListeNumeros[j - 1] = listeNumeros[j];
        }
        listeNumeros = nouvelleListeNumeros;
        return true;
    }

    public java.lang.String toString(){
        String sequence = "";
        for( int i = 0 ; i < listeNumeros.length ; i++ ){
            sequence += listeNumeros[i].toString() + " ; ";
        }
        return sequence;
    }
}
