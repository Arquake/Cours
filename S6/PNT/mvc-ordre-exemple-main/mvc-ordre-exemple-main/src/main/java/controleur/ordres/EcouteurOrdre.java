package controleur.ordres;

public interface EcouteurOrdre {

    /**
     * Permet à un abonné de s'inscrire au près
     * d'un GenerateurEvenement
     * @param g : le générateur concerné
     */
    void setAbonnement(LanceurOrdre g);

    /**
     * Permet de décrire le traitement en fonction
     * de l'événement reçu
     * @param e
     */
    void traiter(TypeOrdre e);
}
