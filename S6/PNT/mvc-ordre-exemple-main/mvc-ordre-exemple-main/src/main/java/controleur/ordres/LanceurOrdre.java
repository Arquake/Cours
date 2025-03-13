package controleur.ordres;

public interface LanceurOrdre {
    /**
     * Permet d'enregistrer un abonné pour
     * différents types d'événements
     * @param ecouteurOrdre
     * @param types
     */
    void abonnement(EcouteurOrdre ecouteurOrdre,
                    TypeOrdre... types);

    /**
     * Permet de diffuser un événement
     * aux abonnés concernés
     * @param e
     */
    void fireOrdre(TypeOrdre e);

}