package facadeLudotheque;

import modeleLudotheque.Jeu;

import java.util.Collection;

public interface FacadeLudotheque {

    /**
     * Permet de récupérer toutes les catégories
     * @return
     */
    Collection<String> getAlls();

    /**
     * Permet de récupérer tous les jeux d'une catégorie donnée
     * @param categorie : nom de la catégorie visée
     * @return tous les jeux de la catégorie visée
     * @throws CategorieNotFoundException
     */
    Collection<Jeu> getJeuxDuneCategorie(String categorie) throws CategorieNotFoundException;

    /**
     * Permet d'ajouter un nouveau jeu dans la ludotheque
     * @param nomJeu : nom du jeu
     * @param categorie : catégorie du jeu (chaîne de caractères correspondant à CategorieJeu
     * @param presentation : résumé du jeu
     * @return
     * @throws JeuDejaExistant : un jeu avec le même nom existe déja
     * @throws CategorieNotFoundException : la catégorie précisée n'existe pas
     */
    Integer ajoutJeu(String nomJeu, String categorie,String presentation) throws JeuDejaExistant,
            CategorieNotFoundException,InformationManquanteException;

    /**
     * Permet de récupérer un jeu par son identifiant
     * @param id : id du jeu
     * @return le jeu correspondant
     * @throws JeuNotFoundException : l'id ne correspond à aucun jeu
     */
    Jeu getJeuById(Integer id) throws JeuNotFoundException;

}
