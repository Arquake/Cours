package fr.info.orleans.ws.controleur;

public interface LanceurOrdre {
    /**
     * Permet d'enregistrer un abonné pour
     * différents types d'événements
     * @param ecouteurOrdre
     * @param ordres
     */
    void abonnement(EcouteurOrdre ecouteurOrdre,
                    Ordres... ordres);
    /**
     * Permet de diffuser un événement
     * aux abonnés concernés
     * @param ordre
     */
    void fireOrdre(Ordres ordre);

}
