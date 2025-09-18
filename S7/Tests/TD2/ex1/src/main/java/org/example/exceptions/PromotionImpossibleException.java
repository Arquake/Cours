package org.example.exceptions;

public class PromotionImpossibleException extends Exception {
    public PromotionImpossibleException() {
        super("Il est impossible d'affecter cette promotion au produit");
    }
}
