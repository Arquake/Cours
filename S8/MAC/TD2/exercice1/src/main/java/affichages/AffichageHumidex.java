package affichages;

import modele.EventType;

import java.util.function.Predicate;

public class AffichageHumidex implements Affichage {

    private double temperature;
    private double humidite;

    public void afficher() {
        System.out.println("Conditions ressenti: " + temperature*humidite + "°C");
    }

    @Override
    public void update(double value, EventType e) {
        switch (e) {
            case TEMPERATURE -> temperature = value;
            case HUMIDITY -> humidite = value;
        }
    }

    @Override
    public Predicate<EventType> getContract() {
        return eventType -> eventType == EventType.TEMPERATURE || eventType == EventType.HUMIDITY;
    }
}
