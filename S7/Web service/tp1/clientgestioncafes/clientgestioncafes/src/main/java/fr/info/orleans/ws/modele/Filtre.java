package fr.info.orleans.ws.modele;

import java.time.LocalDate;

public class Filtre {
    boolean perso;
    LocalDate debut;
    LocalDate fin;

    public Filtre(boolean perso, LocalDate debut, LocalDate fin) {
        this.perso = perso;
        this.debut = debut;
        this.fin = fin;
    }

    public boolean isPerso() {
        return perso;
    }

    public void setPerso(boolean perso) {
        this.perso = perso;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
}
