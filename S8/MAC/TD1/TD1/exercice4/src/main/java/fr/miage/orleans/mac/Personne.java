package fr.miage.orleans.mac;

import java.util.Collection;
import java.util.HashSet;

public class Personne {
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String email;
    private String numeroTelephone;
    private Collection<Contact> contacts;
    private Personne(){}

    public Personne(PersonneBuilder b) {
        this.nom = b.nom;
        this.prenom = b.prenom;
        this.adresse = b.adresse;
        this.ville = b.ville;
        this.email = b.email;
        this.numeroTelephone = b.numeroTelephone;
        this.contacts = b.contacts;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", contacts=" + contacts +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    public static class PersonneBuilder {

        private String nom;
        private String prenom;
        private String adresse;
        private String ville;
        private String email;
        private String numeroTelephone;
        private Collection<Contact> contacts;

        public PersonneBuilder() {
            contacts = new HashSet<>();
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public void setVille(String ville) {
            this.ville = ville;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setNumeroTelephone(String numeroTelephone) {
            this.numeroTelephone = numeroTelephone;
        }

        public void addContact(SocialNetwork sn, String id) {
            contacts.add(new Contact(sn, id));
        }

        public Personne build() {
            return new Personne(this);
        }
    }
}

