package org.example.facade;

import org.example.exceptions.ProduitInexistantException;
import org.example.exceptions.PromotionImpossibleException;
import org.example.model.Categorie;
import org.example.model.Produit;

import java.util.UUID;

public interface IFacadeMagasin {

    String afficherProduits();

    void promotionnerCategorie(Categorie cat, double promotion) throws PromotionImpossibleException, ProduitInexistantException;

    void ajouterProduit(Produit produit) throws ProduitInexistantException;
}
