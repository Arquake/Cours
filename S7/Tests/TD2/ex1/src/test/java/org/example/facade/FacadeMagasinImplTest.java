package org.example.facade;

import org.example.exceptions.ProduitInexistantException;
import org.example.exceptions.PromotionImpossibleException;
import org.example.model.Categorie;
import org.example.model.Produit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FacadeMagasinImplTest {

    IFacadeMagasin facade;

    @BeforeEach
    void init() {
        facade = new FacadeMagasinImpl();
    }

    @Test
    void addingProductNoException() {
        Categorie cat1 = new Categorie(1, 0);
        Produit p = new Produit("P1", 10f, cat1);

        Assertions.assertDoesNotThrow(()->facade.ajouterProduit(p));
    }

    @Test
    void loweringPriceNoException() {
        Categorie cat1 = new Categorie(1, 0);
        Produit p = new Produit("P1", 10f, cat1);

        Assertions.assertDoesNotThrow(()->facade.ajouterProduit(p));
        Assertions.assertDoesNotThrow(()->facade.promotionnerCategorie(cat1, .25d));
    }

    @Test
    void rightPriceReturnedWithReduction() {
        Categorie cat1 = new Categorie(1, 0);
        Produit p = new Produit("P1", 10f, cat1);

        Assertions.assertDoesNotThrow(()->facade.ajouterProduit(p));
        Assertions.assertDoesNotThrow(()->facade.promotionnerCategorie(cat1, .25d));
        Assertions.assertEquals(p.getPrix(), 7.5f);
    }

    @Test
    void productAddDontExistsNull() {
        Assertions.assertThrows(ProduitInexistantException.class, () -> facade.ajouterProduit(null));
    }

    @Test
    void promotionTooBig() {
        Categorie cat1 = new Categorie(1, 0);
        Produit p = new Produit("P1", 10f, cat1);

        Assertions.assertDoesNotThrow(()->facade.ajouterProduit(p));
        Assertions.assertThrows(PromotionImpossibleException.class, ()->facade.promotionnerCategorie(cat1, 101));
    }

    @Test
    void promotionNegative() {
        Categorie cat1 = new Categorie(1, 0);
        Produit p = new Produit("P1", 10f, cat1);

        Assertions.assertDoesNotThrow(()->facade.ajouterProduit(p));
        Assertions.assertThrows(PromotionImpossibleException.class, ()->facade.promotionnerCategorie(cat1, -1));
    }

    @Test
    void promotionNotAffectingOthers() {
        Categorie cat1 = new Categorie(1, 0);
        Categorie cat2 = new Categorie(1, 0);
        Produit p = new Produit("P1", 10f, cat1);
        Produit p2 = new Produit("P2", 10f, cat2);

        Assertions.assertDoesNotThrow(()->facade.ajouterProduit(p));
        Assertions.assertDoesNotThrow(()->facade.ajouterProduit(p2));
        Assertions.assertDoesNotThrow(()->facade.promotionnerCategorie(cat1, .5d));
        Assertions.assertEquals(10f, p2.getPrix());
    }

    // tests structurels
    @Test
    void promotionCat1() {
        Categorie cat1 = new Categorie(1, .2);
        Produit p = new Produit("P1", 10f, cat1);

        Assertions.assertEquals(8f, p.getPrix());
    }

    @Test
    void promotionCat2() {
        Categorie cat2 = new Categorie(2, .5);
        Produit p = new Produit("P1", 10f, cat2);

        Assertions.assertEquals(5f, p.getPrix());
    }

    @Test
    void promotionCat3() {
        Categorie cat3 = new Categorie(3, .7);
        Produit p = new Produit("P1", 10f, cat3);

        Assertions.assertEquals(3f, p.getPrix());
    }
}