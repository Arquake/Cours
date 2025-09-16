package fr.orleans.m1miage.wsi.monpremierwebservice.modele;

import java.util.UUID;

public class Etudiant {
    private String numeroEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String adresse;
    public Etudiant(String nomEtudiant, String prenomEtudiant, String adresse) {
        this.numeroEtudiant = UUID.randomUUID().toString();
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.adresse = adresse;
    }
    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }
    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }
    public String getNomEtudiant() {
        return nomEtudiant;
    }
    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }
    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }
    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
