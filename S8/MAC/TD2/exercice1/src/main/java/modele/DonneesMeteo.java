package modele;

import affichages.Affichage;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.affichages.addAll(List.of(affichages));
    }

    public void actualiserMesures() {
        actualiserHumidite();
        actualiserTemperature();
        actualiserPression();
    }

    private void actualiserTemperature() {
        double temp = getTemperature();
        for (Affichage a: affichages) {
            if (a.getContract().test(EventType.TEMPERATURE)) a.update(temp, EventType.TEMPERATURE);
        }
    }

    private void actualiserHumidite() {
        double humidite = getHumidite();
        for (Affichage a: affichages) {
            if (a.getContract().test(EventType.HUMIDITY)) a.update(humidite, EventType.HUMIDITY);
        }
    }

    private void actualiserPression() {
        double pression = getPression();
        for (Affichage a: affichages) {
            if (a.getContract().test(EventType.PRESSION)) a.update(pression, EventType.PRESSION);
        }
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

    public void subscribe(Affichage... a) {
        affichages.addAll(List.of(a));
    }
}