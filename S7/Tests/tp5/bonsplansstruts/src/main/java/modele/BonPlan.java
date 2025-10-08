package modele;



import java.time.LocalDateTime;

public interface BonPlan {

    int getId();
    String getThematique();
    String getDescription();
    String getLien();
    double getPrix();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();

    default void updateAccording(BonPlan bonPlan){};

    default void likerBonPlan(Utilisateur utilisateur)  {};

    default void dislikerBonPlan(Utilisateur utilisateur)  {};

    long getNbLikers();
    long getNbDislikers();
}
