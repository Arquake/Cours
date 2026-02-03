package affichages;

import modele.EventType;

import java.util.function.Predicate;

public class AffichageConditions implements Affichage {

    private double temperature;
    private double humidite;
    private double pression;

    public void afficher() {
        System.out.println("Conditions actuelles: " + temperature
                + " °C ; pression " + pression + " et " + humidite + "% d'humidité");
    }

    @Override
    public void update(double value, EventType e) {
        switch (e) {
            case HUMIDITY -> humidite = value;
            case PRESSION -> pression = value;
            case TEMPERATURE -> temperature = value;
        }
    }

    @Override
    public Predicate<EventType> getContract() {
        return eventType -> eventType == EventType.TEMPERATURE || eventType == EventType.PRESSION || eventType == EventType.HUMIDITY;
    }
}
