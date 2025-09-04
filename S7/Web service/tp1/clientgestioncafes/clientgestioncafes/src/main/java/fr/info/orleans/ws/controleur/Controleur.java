package fr.info.orleans.ws.controleur;


import fr.info.orleans.ws.modele.*;

import fr.info.orleans.ws.vues.GestionnaireVue;

import java.time.LocalDate;
import java.util.*;

public class Controleur implements LanceurOrdre {


    Map<Ordres, Collection<EcouteurOrdre>> ecouteursOrdres = new HashMap<>();
    GestionnaireVue gestionnaireVue;
    ProxyGestionCafeWS proxyGestionCafeWS = new ProxyGestionCafeWSImpl();
    Filtre filtreCourant = new Filtre(false, null, null);


    public Controleur(GestionnaireVue gestionnaireVue) {
        this.gestionnaireVue = gestionnaireVue;
        this.initialisation();
        gestionnaireVue.setControleur(this);
        gestionnaireVue.setAbonnement(this);

    }

    private void initialisation() {
        Arrays.stream(Ordres.values()).forEach(ordre -> {
            ecouteursOrdres.put(ordre, new ArrayList<>());
        });
    }




    @Override
    public void abonnement(EcouteurOrdre ecouteurOrdre, Ordres... ordres) {
        for (Ordres ordre : ordres) {
            ecouteursOrdres.get(ordre).add(ecouteurOrdre);
        }
    }

    @Override
    public void fireOrdre(Ordres ordre) {
        for (EcouteurOrdre ecouteurOrdre : ecouteursOrdres.get(ordre)) {
            ecouteurOrdre.traiter(ordre);
        }
    }

    private String cleSecrete;

    private UtilisateurDTO utilisateurIdentifie;

    public void inscrire(String emailText, String nomText, String prenomText) {
        try {
            this.cleSecrete = proxyGestionCafeWS.inscription(emailText, nomText, prenomText);
            utilisateurIdentifie = proxyGestionCafeWS.connexion(cleSecrete);
            this.fireOrdre(Ordres.INSCRIPTION_OK);
            this.fireOrdre(Ordres.DATA_UPDATED);
            this.fireOrdre(Ordres.SHOW_GESTION);

        } catch (ConflitEmailException e) {
            this.fireOrdre(Ordres.ERREUR_INSCRIPTION_EXISTANT);
        } catch (DonneesIncompletesException e) {
            this.fireOrdre(Ordres.ERREUR_INSCRIPTION_INCOMPLETE);
        } catch (MauvaiseCleSecreteException e) {
            throw new RuntimeException("La clé vient d'être générée !!!");
        }
    }

    public void connexion(String cleSecreteText) {
        try {
            utilisateurIdentifie = this.proxyGestionCafeWS.connexion(cleSecreteText);
            cleSecrete = cleSecreteText;
            this.fireOrdre(Ordres.DATA_UPDATED);
            this.fireOrdre(Ordres.SHOW_GESTION);
        } catch (MauvaiseCleSecreteException e) {
            this.fireOrdre(Ordres.ERREUR_CONNEXION);
        }
    }

    public void run() {
        fireOrdre(Ordres.SHOW_MENU);
    }

    public String getCleSecrete() {
        return cleSecrete;
    }

    public Collection<RechargeCafeDTO> getRecharges() {
        return proxyGestionCafeWS.getRecharges(this.cleSecrete,this.filtreCourant);
    }


    public void filtrer(boolean persoSelected, LocalDate debutValue, LocalDate finValue) {
        this.filtreCourant.setPerso(persoSelected);
        this.filtreCourant.setDebut(debutValue);
        this.filtreCourant.setFin(finValue);
        this.fireOrdre(Ordres.DATA_UPDATED);

    }

    public void creerRecharge(int nbKilos) {
        try {
            proxyGestionCafeWS.creerRecharge(this.cleSecrete, nbKilos);
            this.fireOrdre(Ordres.RECHARGE_CREATION_OK);
            this.fireOrdre(Ordres.DATA_UPDATED);
        } catch (PoidsIncorrectException e) {
            this.fireOrdre(Ordres.ERREUR_NB_KILOS);
        }
    }
}
