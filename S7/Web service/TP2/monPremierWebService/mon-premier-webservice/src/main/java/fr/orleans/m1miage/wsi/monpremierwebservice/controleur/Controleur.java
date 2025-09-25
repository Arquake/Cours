package fr.orleans.m1miage.wsi.monpremierwebservice.controleur;

import fr.orleans.m1miage.wsi.monpremierwebservice.modele.Etudiant;
import fr.orleans.m1miage.wsi.monpremierwebservice.modele.EtudiantInexistantException;
import fr.orleans.m1miage.wsi.monpremierwebservice.modele.FacadePromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/mpws")
public class Controleur {

    // Spring va instantier automatiquement une facade promotion sans avoir à lui écrire le code
    @Autowired
    FacadePromotion facade;

    @PostMapping("/etudiant")
    ResponseEntity<String> ajouterEtudiant (@RequestParam String nom, String prenom, String adresse) {
        if (Objects.isNull(nom) || nom.isBlank() || Objects.isNull(prenom) || prenom.isBlank() || Objects.isNull(adresse) || adresse.isBlank()) {
            return ResponseEntity.badRequest().body("Des données manquantes. Les données obligatoires sont: nom, prénom ou email");
        }

        String id = facade.enregistrerEtudiant(nom, prenom, adresse);

        

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "http://localhost:8080/mpws/etudiant/"+id).body("");
    }

    @GetMapping("/etudiant/{id}")
    ResponseEntity<Etudiant> getEtudiant (@PathVariable String id) {
        try {
            Etudiant etu = facade.getEtudiantById(id);
            return ResponseEntity.ok(etu);
        } catch (EtudiantInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/etudiant")
    ResponseEntity<Collection<Etudiant>> getAllEtudiants() {
        return ResponseEntity.ok(facade.getEtudiants());
    }
}
