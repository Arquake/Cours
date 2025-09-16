package fr.orleans.m1miage.wsi.monpremierwebservice.controleur;

import fr.orleans.m1miage.wsi.monpremierwebservice.modele.FacadePromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controleur {

    // Spring va instantier automatiquement une facade promotion sans avoir à lui écrire le code
    @Autowired
    FacadePromotion facade;
}
