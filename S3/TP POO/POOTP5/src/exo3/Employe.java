package exo3;

import java.time.LocalDate;

public class Employe{
    String name;
    int id;
    double salary;
    LocalDate embauche;

    private static int idIncrement = 1;


    public Employe(String name, LocalDate embauche, double salary) {
        this.name = name;
        this.salary = salary;
        this.embauche = embauche;
        this.id = idIncrement;
        idIncrement++;
    }

    @Override
    public String toString() {
        return ("nom : " + this.name + " id : " + this.id + " salaire : " + this.salary + " Date d'embauche : " + this.embauche);
    }

    public double calculerSalaireBrutMensuel() {
        LocalDate today = LocalDate.now();
        int employedYears = today.getYear() - this.embauche.getYear();
        if ( employedYears < 3 ) {
            return salary;
        } else if ( employedYears < 6 ) {
            return salary * 1.04;
        } else if ( employedYears < 9 ) {
            return salary * 1.07;
        }
        return salary * 1.1;
    }

    public int getId() {
        return id;
    }
}