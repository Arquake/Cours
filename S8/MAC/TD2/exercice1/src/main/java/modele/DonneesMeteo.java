package modele;

import affichages.Affichage;

import java.util.ArrayList;
import java.util.List;

public class DonneesMeteo {
    List<Affichage> affichages;
    private String nom;
    // valeurs des capteurs
    private double temperature;
    private double humidite;
    private double pression;

    public DonneesMeteo(String nom, Affichage... affichages) {
        this.nom = nom;
        this.affichages = new ArrayList<>();
        for (Affichage affichage : affichages)
            this.affichages.add(affichage);
    }

    public void actualiserMesures() {
        double temp = getTemperature();
        double humidite = getHumidite();
        double pression = getPression();

        for(Affichage affichage : affichages)
            affichage.actualiser(temp, humidite, pression);
    }

    public String getNom() {
        return nom;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidite() {
        return humidite;
    }

    public double getPression() {
        return pression;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidite(double humidite) {
        this.humidite = humidite;
    }

    public void setPression(double pression) {
        this.pression = pression;
    }
}