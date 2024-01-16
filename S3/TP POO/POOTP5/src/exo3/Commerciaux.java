package exo3;

import exo3.Employe;

import java.time.LocalDate;

public class Commerciaux extends Employe {

    double venteDernierMois;
    public Commerciaux(String name, LocalDate embauche, double salary) {
        super(name, embauche, salary);
    }

    @Override
    public double calculerSalaireBrutMensuel() {
        LocalDate today = LocalDate.now();
        int employedYears = today.getYear() - this.embauche.getYear();
        if ( employedYears < 3 ) {
            return salary + venteDernierMois * 0.05;
        } else if ( employedYears < 6 ) {
            return salary * 1.04 + venteDernierMois * 0.05;
        } else if ( employedYears < 9 ) {
            return salary * 1.07 + venteDernierMois * 0.05;
        }
        return salary * 1.1 + venteDernierMois * 0.05;
    }

    public void setVenteDernierMois(double venteDernierMois) {
        this.venteDernierMois = venteDernierMois;
    }

    @Override
    public String toString() {
        return "commercial " + super.toString() + " Ventes du dernier mois : " + venteDernierMois;
    }
}
