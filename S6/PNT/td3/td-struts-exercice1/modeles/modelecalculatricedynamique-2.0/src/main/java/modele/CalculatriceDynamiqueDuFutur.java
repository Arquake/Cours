package modele;

import modele.exceptions.NonSupporteeException;

import java.util.Collection;

public interface CalculatriceDynamiqueDuFutur {


    /**
     *
     * @return la liste des opérations gérées par cette calculatrice
     */
    Collection<String> getOperations();


    /**
     *
     * @return le nombre d'opérations faites avec cette calculatrice depuis sa création
     */
    long getValeurCompteur();


    /**
     *
     * @param operation type d'opération à faire
     * @param operande1 opérande la plus "à gauche"
     * @param operande2
     * @return opérande la plus "à gauche"
     */

    double doCalcul(String operation, double operande1, double operande2) throws NonSupporteeException;
}
