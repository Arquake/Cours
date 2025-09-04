package fr.info.orleans.wsi.cafesws.modele;

import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FacadeGestionCafes {

    /**
     * Utilisateurs indexés par l'email
     */
    private Collection<Utilisateur> utilisateurs;

    private Collection<RechargeCafe> rechargesCafes;

    public FacadeGestionCafes() {
        this.utilisateurs = new ArrayList<>();
        this.rechargesCafes = new ArrayList<>();
    }


    public String inscription(String nom, String prenom, String email) throws EmailDejaPrisException {
        if (utilisateurs.stream().filter(u -> u.getEmail().equals(email)).count()>0)
            throw new EmailDejaPrisException();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setIdAleatoire(UUID.randomUUID().toString());
        utilisateurs.add(utilisateur);
        return utilisateur.getIdAleatoire();
    }


    public String rechargeCafe(String idUtilisateur,  int nbKilos) throws UtilisateurInexistantException, DonneeIncorrecteException {
        LocalDate dateRecharge = LocalDate.now();
        Utilisateur utilisateur = utilisateurs.stream()
                .filter(u -> u.getIdAleatoire().equals(idUtilisateur)).findFirst()
                .orElseThrow(() -> new UtilisateurInexistantException());
        if (nbKilos < 0) {
            throw new DonneeIncorrecteException();
        }
        RechargeCafe rechargeCafe = new RechargeCafe(utilisateur,nbKilos,dateRecharge);
        rechargesCafes.add(rechargeCafe);
        return rechargeCafe.getId();

    }


    public Collection<RechargeCafe> getRechargesCafes(String idUtilisateur) throws UtilisateurInexistantException {
        Utilisateur utilisateur = utilisateurs.stream()
                .filter(u -> u.getIdAleatoire().equals(idUtilisateur)).findFirst()
                .orElseThrow(() -> new UtilisateurInexistantException());
        return rechargesCafes.stream()
                .collect(Collectors.toList());
    }

    public void modifierRechargeCafeKilos(String idUtilisateur, String idRecharge,int nbKilos) throws UtilisateurInexistantException, OperationNonAuthoriseeException, DonneeIncorrecteException {
        Utilisateur utilisateur = utilisateurs.stream()
                .filter(u -> u.getIdAleatoire().equals(idUtilisateur)).findFirst()
                .orElseThrow(() -> new UtilisateurInexistantException());
        RechargeCafe rechargeCafe = rechargesCafes.stream()
                .filter(r -> r.getId().equals(idRecharge)).filter(r -> r.getUtilisateur().equals(utilisateur)).findFirst()
                        .orElseThrow(() -> new OperationNonAuthoriseeException());

        if (nbKilos <= 0) {
            throw new DonneeIncorrecteException();
        }
       rechargeCafe.setNbKilos(nbKilos);
    }

    public void modifierRechargeCafeDate(String idUtilisateur, String idRecharge, LocalDate dateRecharge) throws UtilisateurInexistantException, OperationNonAuthoriseeException {
        Utilisateur utilisateur = utilisateurs.stream()
                .filter(u -> u.getIdAleatoire().equals(idUtilisateur)).findFirst()
                .orElseThrow(() -> new UtilisateurInexistantException());
        RechargeCafe rechargeCafe = rechargesCafes.stream()
                .filter(r -> r.getId().equals(idRecharge)).filter(r -> r.getUtilisateur().equals(utilisateur)).findFirst()
                        .orElseThrow(() -> new OperationNonAuthoriseeException());
        rechargeCafe.setDateRecharge(dateRecharge);
    }

    public Utilisateur getUtilisateur(String id) throws UtilisateurInexistantException {
        return utilisateurs.stream().filter(u -> u.getIdAleatoire().equals(id))
                .findFirst().orElseThrow(() -> new UtilisateurInexistantException());
    }

    public RechargeCafe getRechargeCafeByID(String cleSecrete, String id) throws UtilisateurInexistantException, RechargeCafeInexistanteException {
        Utilisateur utilisateur = utilisateurs.stream()
                .filter(u -> u.getIdAleatoire().equals(cleSecrete)).findFirst()
                .orElseThrow(() -> new UtilisateurInexistantException());
        RechargeCafe rechargeCafe = rechargesCafes.stream().filter(r -> r.getId().equals(id)).findFirst().orElseThrow(
                () -> new RechargeCafeInexistanteException()
        );
        return rechargeCafe;
    }
}
