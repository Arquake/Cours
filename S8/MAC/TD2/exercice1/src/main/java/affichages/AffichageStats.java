package affichages;

import modele.EventType;

import java.util.function.Predicate;

public class AffichageStats implements Affichage {
    private double tempMax = 0.0f;
    private double tempMin = 200;
    private double somTemp= 0.0f;
    private int nbLectures;

    public void afficher() {
        System.out.println("Température Moy/Max/Min = " + (somTemp / nbLectures)
                + "/" + tempMax + "/" + tempMin);
    }

    @Override
    public void update(double value, EventType e) {
        if (e == EventType.TEMPERATURE) {
            tempMax = Math.max(tempMax, value);
            tempMin = Math.min(tempMin, value);
            somTemp += value;
            nbLectures++;
        }
    }

    @Override
    public Predicate<EventType> getContract() {
        return eventType -> eventType == EventType.TEMPERATURE;
    }
}
