package modele;

import java.time.LocalDateTime;

public interface FabriqueBonPlan {

    BonPlan creer(int id, String description, String theme, String lien, double prix, LocalDateTime debut, LocalDateTime fin);
}
