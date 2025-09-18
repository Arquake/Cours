package org.example.exceptions;

public class ProduitInexistantException extends Exception {
    public ProduitInexistantException() {
        super("Le produit n'existe pas");
    }
}
