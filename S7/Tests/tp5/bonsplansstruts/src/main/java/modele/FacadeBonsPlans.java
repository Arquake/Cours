package modele;

import modele.exceptions.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FacadeBonsPlans {

    /**
     * Permet de s'identifier dans l'application
     * @param email : email étudiant
     * @param motDePasse : mot de passe étudiant
     * @return : retourne une chaîne aléatoire soumise
     * à une date d'expiration
     */

    String identification(String email, String motDePasse) throws IdentifiantsIncorrectsException;


    /**
     * Permet à un utilisateur de candidater comme modérateur
     * @param token : token de l'utilisateur en question
     * @throws CandidatureDejaDeposeeException : la candidature est déjà déposée
     * @throws DejaModerateurException : l'utilisateur est déjà modérateur
     * @throws UtilisateurInconnuException : l'utilisateur n'existe pas
     * @throws TokenExpireException : le token est expire il faut le renouveler
     */

    void candidaterModerateur(String token)
            throws CandidatureDejaDeposeeException,
            DejaModerateurException,
            UtilisateurInconnuException, TokenExpireException;

    /**
     * Permet à un modérateur de valider une candidature
     * @param tokenModerateur
     * @param email
     * @throws DroitsInsuffisantsException
     * @throws UtilisateurInconnuException
     * @throws CandidatureInconnueException
     * @throws TokenExpireException
     */

    void validerModerateur(String tokenModerateur,String email) throws
            DroitsInsuffisantsException, UtilisateurInconnuException, CandidatureInconnueException, TokenExpireException;

    /**
     * Permet à un modérateur de refuser une candidature.
     * @param tokenModerateur
     * @param email
     * @throws DroitsInsuffisantsException
     * @throws UtilisateurInconnuException
     * @throws CandidatureInconnueException
     * @throws TokenExpireException
     */
    void refuserModerateur(String tokenModerateur,String email) throws
            DroitsInsuffisantsException, UtilisateurInconnuException, CandidatureInconnueException, TokenExpireException;

    /**
     * Permet de récupérer la liste des candidatures
     * @param tokenModerateur
     * @return
     * @throws DroitsInsuffisantsException
     * @throws UtilisateurInconnuException
     * @throws TokenExpireException
     */
    Collection<Utilisateur> getListeCandidatureModerateur(String tokenModerateur) throws DroitsInsuffisantsException,
            UtilisateurInconnuException, TokenExpireException;

    /**
     * Permet de créer un bon plan
     * @param token : token de l'utilisateur
     * @param theme : thème du bon plan
     * @param description : description du bon plan
     * @param lien : contacts pour le bon plan
     * @param prix : prix du bon plan
     * @param dateDebut : date de début du bon plan
     * @param dateFin : date de fin du bon plan
     * @return identifiant du bon plan
     * @throws ParametresManquantsException
     * @throws UtilisateurInconnuException
     * @throws DatesIncoherentesException
     * @throws PrixIncoherentException
     * @throws TokenExpireException
     */
    int creerBonPlan(String token, String theme,
                        String description, String lien,
                        double prix, LocalDateTime dateDebut,
                        LocalDateTime dateFin) throws ParametresManquantsException,
                        UtilisateurInconnuException, DatesIncoherentesException,
                        PrixIncoherentException, TokenExpireException;

    /**
     * Permet de modifier un bon plan si nous en avons le droit
     * @param token : token de l'utilisateur souhaitant modifier le bon plan
     * @param bonPlanDTO : modifications souhaitées
     * @throws DroitsInsuffisantsException : l'utilisateur n'a pas le droit de modifier ce bon plan.
     * Soit parce qu'il n'est pas modérateur,
     * soit parce que le bon plan ne lui appartient pas.
     * @throws UtilisateurInconnuException
     * @throws BonPlanInconnuException
     * @throws TokenExpireException
     */


    void modifierBonPlan(String token, BonPlan bonPlanDTO) throws
            DroitsInsuffisantsException, UtilisateurInconnuException,
            BonPlanInconnuException, TokenExpireException;


    /**
     * Permet de récupérer les bons plans. Possibilité de filtrer selon un thème.
     * @param token
     * @param theme : Optional.empty() si aucun thème, non vide sinon
     * @return : les bons plans concernés
     * @throws UtilisateurInconnuException
     * @throws TokenExpireException
     */

    Collection<BonPlan> getBonsPlans(String token, Optional<String> theme) throws UtilisateurInconnuException, TokenExpireException;


    /**
     * Permet à un utilisateur de récupérer tous ses bons plans.
     * @param token
     * @param theme
     * @return ses bons plans
     * @throws UtilisateurInconnuException
     * @throws TokenExpireException
     */
    Collection<BonPlan> getMesBonsPlans(String token, Optional<String> theme) throws UtilisateurInconnuException, TokenExpireException;

    /**
     * Permet de liker un bon plan
     * @param token
     * @param idBonPlan
     * @throws UtilisateurInconnuException
     * @throws BonPlanInconnuException
     * @throws TokenExpireException
     */
    void likerBonPlan(String token, int idBonPlan) throws
            UtilisateurInconnuException, BonPlanInconnuException,  TokenExpireException;

    /**
     * Permet de disliker un bon plan
     * @param token
     * @param idBonPlan
     * @throws UtilisateurInconnuException
     * @throws BonPlanInconnuException
     * @throws TokenExpireException
     */
    void dislikerBonPlan(String token, int idBonPlan) throws
            UtilisateurInconnuException, BonPlanInconnuException,  TokenExpireException;

    /**
     * Permet de récupérer les bons plans triés par popularité décroissante.
     * @param token
     * @return
     * @throws UtilisateurInconnuException
     * @throws TokenExpireException
     */
    List<BonPlan> getBonsPlansParPopularite(String token) throws UtilisateurInconnuException, TokenExpireException;

    /**
     * Permet de récupérer tous les thèmes existants
     * @param token
     * @return
     * @throws UtilisateurInconnuException
     * @throws TokenExpireException
     */
    List<String> getThemes(String token) throws UtilisateurInconnuException, TokenExpireException;


    /**
     * Permet de récupérer un utilisateur à partir d'un token
     * @param token
     * @return
     * @throws UtilisateurInconnuException
     * @throws TokenExpireException
     */
    Utilisateur getUtilisateur(String token) throws UtilisateurInconnuException, TokenExpireException;

    /**
     * Permet de récupérer un bon plan par son ID.
     * @param toke
     * @param id
     * @return
     * @throws BonPlanInconnuException
     * @throws TokenExpireException
     * @throws UtilisateurInconnuException
     */
    BonPlan getBonPlanById(String toke, int id) throws BonPlanInconnuException, TokenExpireException, UtilisateurInconnuException;

}
