package fr.info.orleans.ws.modele;

import java.util.Collection;

public interface ProxyGestionCafeWS {
    /**
     * Permet d'inscrire un utilisateur
     * @param emailText : l'email de l'utilisateur
     * @param nomText : le nom de l'utilisateur
     * @param prenomText : le prénom de l'utilisateur
     * @return la clef secrète de l'utilisateur
     * @throws ConflitEmailException : l'email existe déjà
     * @throws DonneesIncompletesException : les données sont incomplètes
     */
    String inscription(String emailText, String nomText, String prenomText) throws ConflitEmailException, DonneesIncompletesException;


    /**
     * Permet de se connecter à l'application en récupérant le compte liée à la clé secrète
     * @param cleSecreteText : la clé secrète
     * @return le compte lié
     * @throws MauvaiseCleSecreteException : la clé secrète n'est pas connue
     */
    UtilisateurDTO connexion(String cleSecreteText) throws MauvaiseCleSecreteException;


    /**
     * Permet de récupérer les recharges de cafés en fonction d'un filtre
     * @param cleSecrete : la clé secrète de l'utilisateur connecté
     * @param filtreCourant : le filtre courant précisant la période concernée ou le fait qu'uniquement
     *                      les recharges de l'utilisateur en question sont retournées
     * @return la liste des recharges satisfaisant le filtre
     */
    Collection<RechargeCafeDTO> getRecharges(String cleSecrete, Filtre filtreCourant);


    /**
     * Permet de créer une rechage de café
     * @param cleSecrete : la clé secrète de l'utilisateur
     * @param nbKilos : le poids de la recharge de café (la date sera celle du jour)
     * @return l'identifiant aléatoire de la recharge
     * @throws PoidsIncorrectException : le poids est incorrect (<=0)
     */
    String creerRecharge(String cleSecrete, int nbKilos) throws PoidsIncorrectException;
}
