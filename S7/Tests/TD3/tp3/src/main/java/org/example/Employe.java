package org.example;

public class Employe {
     int annee;//anciennete dans l'entreprise
    double salaireDeBase;//salaire de depart à l'embauche

    public Employe(int a, double s){
        annee=a;
        salaireDeBase=s;
    }

    public int getAnnee(){return annee;}

    public double getSalaireDeBase() {
        return salaireDeBase;
    }

    public void setAnnee(int a){this.annee=a;}

    public void setSalaireDeBase(double s){this.salaireDeBase=s;}

    protected Categorie creerCategorie() {
        return new Categorie();
    }

    //salaire effectif de l employe calcule selon son anciennete sa categorie
    public double salaire() {
      return 0;
    }

}
