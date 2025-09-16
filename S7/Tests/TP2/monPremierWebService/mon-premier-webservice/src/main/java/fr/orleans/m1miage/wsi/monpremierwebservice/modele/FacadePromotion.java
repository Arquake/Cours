package fr.orleans.m1miage.wsi.monpremierwebservice.modele;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class FacadePromotion {
    Map<String,Etudiant> etudiants;
    public FacadePromotion() {
        this.etudiants = new HashMap<>();
    }
    public String enregistrerEtudiant(String nom, String prenom, String adresse) {
        Etudiant etudiant = new Etudiant(nom,prenom,adresse);
        this.etudiants.put(etudiant.getNumeroEtudiant(),etudiant);
        return etudiant.getNumeroEtudiant();
    }
    public Collection<Etudiant> getEtudiants() {
        return this.etudiants.values();
    }
    public Etudiant getEtudiantById(String numeroEtudiant) throws EtudiantInexistantException {
        if (this.etudiants.containsKey(numeroEtudiant)) {
            return this.etudiants.get(numeroEtudiant);
        }
        throw new EtudiantInexistantException();
    }
}