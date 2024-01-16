package exo2;

import java.time.LocalDate;

public class Responsable extends Employe{
    private String titre;
    private int pourcentagePrime;
    private Employe[] lesSubordonnes;

    public Responsable(String name, LocalDate embauche, String titre, int pourcentagePrime, double salary, Employe[] lesSubordonnes) {
        super(name, embauche, salary);
        this.titre = titre;
        this.pourcentagePrime = pourcentagePrime;
        this.lesSubordonnes = lesSubordonnes;
    }

    @Override
    public double calculerSalaireBrutMensuel() {
        LocalDate today = LocalDate.now();
        int employedYears = today.getYear() - this.embauche.getYear();
        if ( employedYears < 3 ) {
            return salary * pourcentagePrime / 100;
        } else if ( employedYears < 6 ) {
            return salary * 1.04 * pourcentagePrime / 100;
        } else if ( employedYears < 9 ) {
            return salary * 1.07 * pourcentagePrime / 100;
        }
        return salary * 1.1 * pourcentagePrime / 100;
    }

    @Override
    public String toString() {
        String chain = "Responsable " + super.toString() + " titre : " + titre + " Liste subordonnées : [ "+lesSubordonnes[0];

        for( int i = 1 ; i < lesSubordonnes.length ; i++ ){
            chain += ", " + lesSubordonnes[i].toString() ;
        }
        chain += " ]";
        return chain;
    }
}
