package fr.univ.orleans.info.m1.ws.tp4.modele;

public class Utilisateur {

    private static int lastId = 0;

    private final String email;
    private final String encodedPassword;
    private final int idUtilisateur;

    public Utilisateur(String email, String encodedPassword) {
        idUtilisateur = lastId++;
        this.email = email;
        this.encodedPassword = encodedPassword;
    }

    public String getEmail() {
        return email;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public boolean verifierPassword(String motDePasse) {
        return this.encodedPassword.equals(motDePasse);
    }

}
