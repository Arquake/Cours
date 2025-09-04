package fr.info.orleans.wsi.cafesws.modele;

import java.time.LocalDate;
import java.util.UUID;

public class RechargeCafe {
    private  LocalDate dateRecharge;
    private  Utilisateur utilisateur;
    private  int nbKilos;
    private final String id;

    public RechargeCafe(Utilisateur utilisateur, int nbKilos, LocalDate dateRecharge) {
        this.utilisateur = utilisateur;
        this.nbKilos = nbKilos;
        this.dateRecharge = dateRecharge;
        this.id = UUID.randomUUID().toString();
    }

    public void setDateRecharge(LocalDate dateRecharge) {
        this.dateRecharge = dateRecharge;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setNbKilos(int nbKilos) {
        this.nbKilos = nbKilos;
    }

    public LocalDate getDateRecharge() {
        return dateRecharge;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public int getNbKilos() {
        return nbKilos;
    }

    public String getId() {
        return id;
    }

}
