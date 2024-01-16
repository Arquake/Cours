package exo3;

import java.time.LocalDate;

public class Emprunt {
    Document document;
    LocalDate dateEmprunt;

    public Emprunt(Document document, LocalDate dateEmprunt) {
        this.document = document;
        this.dateEmprunt = dateEmprunt;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "document=" + document +
                ", dateEmprunt=" + dateEmprunt +
                '}';
    }

    public boolean estEnRetard(){
        int year = (dateEmprunt.getYear() - LocalDate.now().getYear())*365;
        int days = dateEmprunt.getDayOfYear() - LocalDate.now().getDayOfYear();
        return ( year + days > document.getNbJoursAutorises() );
    }
}
