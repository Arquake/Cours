package org.example.facade;

import org.example.exceptions.ProduitInexistantException;
import org.example.exceptions.PromotionImpossibleException;
import org.example.model.Categorie;
import org.example.model.Produit;

import java.util.*;

public class FacadeMagasinImpl implements IFacadeMagasin {

    Map<UUID, Produit> produits;

    public FacadeMagasinImpl() {
        produits = new HashMap<>();
    }

    @Override
    public String afficherProduits() {
        return Arrays.toString(produits.values().toArray());
    }

    @Override
    public void promotionnerCategorie(Categorie cat, double promotion) throws PromotionImpossibleException {
        if (promotion > 1 || promotion < 0) { throw new PromotionImpossibleException(); }
        cat.setReduction(promotion);
    }

    @Override
    public void ajouterProduit(Produit produit) throws ProduitInexistantException {
        if (Objects.isNull(produit)) { throw new ProduitInexistantException(); }
        produits.put(produit.getId(), produit);
    }


}
