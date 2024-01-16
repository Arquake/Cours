package exo1;

public class ClientBis {
    private int idClient; // 0
    private static int nextID;
    static { nextID = 0; }
    {idClient = nextID; nextID += 1;}
    private String nom; // null
    private String prenom; // null
    private String societe; // null
    private boolean actif; // false

    public ClientBis(String nom) {
        this.idClient = idClient;
        this.nom = nom;
    }

    public ClientBis(String nom, String prenom, String societe, boolean actif) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.actif = actif;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSociete() {
        return societe;
    }

    public boolean isActif() {
        return actif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
