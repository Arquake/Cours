package affichages;

import modele.EventType;

import java.util.function.Predicate;

public class AffichagePrevisionsPression implements Affichage {
    private double pression;
    private final double pression_courante = 1012d;

    public void afficher() {
        System.out.print("Prévision : ");
        if (this.pression_courante > this.pression) {
            System.out.println("Amélioration en cours !");
        } else if (this.pression_courante == this.pression) {
            System.out.println("Le temps se rafraîchit");
        } else if (this.pression_courante < this.pression) {
            System.out.println("Dépression bien installée");
        }
    }

    @Override
    public void update(double value, EventType e) {
        if (e == EventType.PRESSION) {
            pression = value;
        }
    }

    @Override
    public Predicate<EventType> getContract() {
        return eventType -> eventType == EventType.PRESSION;
    }
}
