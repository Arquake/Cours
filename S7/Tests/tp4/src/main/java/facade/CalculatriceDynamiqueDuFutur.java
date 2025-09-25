package facade;

import modele.calcul.OperationHistorisee;
import modele.exceptions.NonSupporteeException;
import modele.exceptions.OperationMalFormeeException;

import java.util.Collection;
import java.util.List;

public interface CalculatriceDynamiqueDuFutur {

    /**
     * Permet de récupérer le résultat courant après le signe = ou n'importe quelle
     * opérations +,*,- ou /
     * @return
     * @throws OperationMalFormeeException : erreur quand vous demandez le résultat d'une opération
     * en cours exemple : 1+ et vous demandez le résultat
     */

    double getResultatCourant() throws OperationMalFormeeException;

    /**
     * Permet de saisir un nouveau chiffre dans l'opérande courante
     * @param digit : chiffre de 0 à 9
     */
    void inputDigit(int digit);

    /**
     * Permet d'informer le modèle sur le fait que nous demandons une opération : "+", "-", ...
     * @param op
     * @throws OperationMalFormeeException : peut arriver si êtes en train de saisir un double avec plusieurs ','.
     * Mais notre calculatrice ne devrait pas avoir ce problème.
     * @throws NonSupporteeException : peut arriver si vous demandez une opération qui n'est pas gérée par
     * le modèle : par exemple %
     */
    void inputOperation(String op) throws OperationMalFormeeException, NonSupporteeException;

    /**
     * Permet de saisir une virgule
     */
    void inputDot();

    /**
     * Permet d'éxécuter le calcul
     * @throws OperationMalFormeeException
     * @throws NonSupporteeException
     */

    void inputEqual() throws OperationMalFormeeException, NonSupporteeException;

    /**
     * Permet de récupérer l'opérande courante. Attention l'opérande courante est
     * le nombre que vous êtes en train de saisir avec la calculatrice
     * @return
     */
    String getOperandeCourante();

    /**
     * Permet de récupérer la liste des opérations historisées
     * @return
     */
    List<OperationHistorisee> getHistorique();
}
