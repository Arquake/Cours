package affichages;

import modele.EventType;

import java.util.function.Predicate;

public interface Affichage {
    void update(double value, EventType e);
    Predicate<EventType> getContract();
}
