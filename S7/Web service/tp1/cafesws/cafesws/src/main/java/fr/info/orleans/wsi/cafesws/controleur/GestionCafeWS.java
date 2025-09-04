package fr.info.orleans.wsi.cafesws.controleur;

import fr.info.orleans.wsi.cafesws.dtos.FabriqueDTO;
import fr.info.orleans.wsi.cafesws.dtos.RechargeCafeDTO;
import fr.info.orleans.wsi.cafesws.dtos.UtilisateurDTO;
import fr.info.orleans.wsi.cafesws.modele.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/cafews")
public class GestionCafeWS {

    @Autowired
    FacadeGestionCafes facadeGestionCafes;

    @PostMapping("/compte")
    ResponseEntity<String> ajouterUtilisateur(@RequestBody UtilisateurDTO user) {
        if (Objects.isNull(user)) {
            return ResponseEntity.badRequest().body("Données manquantes");
        }
        if (Objects.isNull(user.getEmail()) || Objects.isNull(user.getNom()) || Objects.isNull(user.getPrenom())
                || user.getEmail().isBlank() || user.getNom().isBlank() || user.getPrenom().isBlank()) {
            return ResponseEntity.badRequest().body("Données obligatoires : nom, prenom, email");
        }
        String id = null;
        try {
            id = facadeGestionCafes.inscription(user.getNom(), user.getPrenom(), user.getEmail());
        } catch (EmailDejaPrisException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déja pris");
        }
        return ResponseEntity.status(HttpStatus.CREATED).header("cleSecrete", id).body(id);
    }

    @GetMapping("/compte/{id}")
    ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable String id) {
        try {
            Utilisateur utilisateur = facadeGestionCafes.getUtilisateur(id);
            return ResponseEntity.ok(FabriqueDTO.utilisateurDTO(utilisateur));
        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/recharge")
    ResponseEntity<String> ajouterRechargeCafe(@RequestHeader String cleSecrete,
                                               @RequestParam int nbKilos, UriComponentsBuilder builder) {
        try {
            String id = facadeGestionCafes.rechargeCafe(cleSecrete, nbKilos);
            return ResponseEntity.status(HttpStatus.CREATED).header("Location", builder.path("/api/cafews/recharge/{id}").buildAndExpand(id).toUriString()).body(id);
        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (DonneeIncorrecteException e) {
            return ResponseEntity.badRequest().body("Nb kilos doit être positif");
        }
    }

    @PatchMapping(value = "/recharge/{id}",consumes ="application/xml")
    ResponseEntity<String> modifierRechargeCafe(@RequestHeader String cleSecrete, @PathVariable String id, @RequestBody RechargeCafeDTO rechargeCafeDTO) {
        if (Objects.isNull(rechargeCafeDTO)) {
            return ResponseEntity.badRequest().body("Données manquantes");
        }

       if (!id.equals(rechargeCafeDTO.getId())) {
           return ResponseEntity.status(409).body("Identifiant objet envoyé incohérent avec la Path variable !");
       }

        if (Objects.nonNull(rechargeCafeDTO.getDateRecharge()) &&
                !rechargeCafeDTO.getDateRecharge().isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                LocalDate date = LocalDate.parse(rechargeCafeDTO.getDateRecharge(), formatter);
                facadeGestionCafes.modifierRechargeCafeDate(cleSecrete, id, date);
                return ResponseEntity.accepted().body("Date modidiée !");

            }
            catch (Exception e) {
                return ResponseEntity.badRequest().body("Format de date invalide");
            } catch (UtilisateurInexistantException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        else {
            try {

                facadeGestionCafes.modifierRechargeCafeKilos(cleSecrete, id, rechargeCafeDTO.getNbKilos());
                return ResponseEntity.ok().build();
            } catch (UtilisateurInexistantException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            } catch (OperationNonAuthoriseeException e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            } catch (DonneeIncorrecteException e) {
                return ResponseEntity.badRequest().body("Nb kilos doit être positif");
            }
        }
    }

    @GetMapping("/recharge/{id}")
    ResponseEntity<RechargeCafeDTO> getRechargeCafe(@RequestHeader String cleSecrete, @PathVariable String id) {
        try {
            RechargeCafe rechargeCafe = facadeGestionCafes.getRechargeCafeByID(cleSecrete, id);
            return ResponseEntity.ok(FabriqueDTO.rechargeCafeDTO(rechargeCafe));
        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (RechargeCafeInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/recharge")
    ResponseEntity<Collection<RechargeCafeDTO>> getRechargesCafes(@RequestHeader String cleSecrete,
            @RequestParam(required = false) Optional<String> dateDebut, @RequestParam(required = false) Optional<String> dateFin,
                                                                  @RequestParam(required = false) Optional<Boolean> perso )  {
        Collection<RechargeCafe> resultat = new ArrayList<>();
        try {
            resultat = facadeGestionCafes.getRechargesCafes(cleSecrete);
        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (dateDebut.isEmpty() && dateFin.isEmpty() && perso.isEmpty()) {
            return ResponseEntity.ok(resultat.stream().map(FabriqueDTO::rechargeCafeDTO).collect(Collectors.toList()));
        }
        else {
            try {

                resultat = perso.isPresent() && perso.get() ?
                        resultat.stream().filter(r -> r.getUtilisateur().getIdAleatoire().equals(cleSecrete)).collect(Collectors.toList()) :
                        resultat;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                LocalDate dfin = dateFin.isEmpty() ? LocalDate.now() :
                        LocalDate.parse(dateFin.get(), formatter);

                resultat = resultat.stream().filter(r -> r.getDateRecharge().isBefore(dfin) || r.getDateRecharge().isEqual(dfin)).collect(Collectors.toList());

                if (dateDebut.isPresent()) {
                    LocalDate ddeb = LocalDate.parse(dateDebut.get(), formatter);
                    resultat = resultat.stream().filter(r -> r.getDateRecharge().isAfter(ddeb)).collect(Collectors.toList());

                }

                return ResponseEntity.ok(resultat.stream().map(FabriqueDTO::rechargeCafeDTO).collect(Collectors.toList()));

            }
            catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }

    }

}
