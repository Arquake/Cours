package fr.info.orleans.wsi.cafesws.dtos;

import fr.info.orleans.wsi.cafesws.modele.RechargeCafe;
import fr.info.orleans.wsi.cafesws.modele.Utilisateur;

import java.time.format.DateTimeFormatter;

public interface FabriqueDTO {

    static UtilisateurDTO utilisateurDTO(Utilisateur u) {
        return new UtilisateurDTO(u.getNom(), u.getPrenom(), u.getEmail());
    }

    static RechargeCafeDTO rechargeCafeDTO(RechargeCafe rechargeCafe) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return new RechargeCafeDTO(rechargeCafe.getId(), rechargeCafe.getDateRecharge().format(formatter).toString(),
                rechargeCafe.getNbKilos(), utilisateurDTO(rechargeCafe.getUtilisateur()));

    }
}
