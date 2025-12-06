package fr.miage.orleans.tokens.modele;

public class Utilisateur {

    private static int lastId = 0;

    private final String email;
    private final String password;
    private final int idUtilisateur;

    public Utilisateur(String email, String password) {
        idUtilisateur = lastId++;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getEncodedPassword() {
        return password;
    }

    public boolean verifierPassword(String motDePasse) {
        return this.password.equals(motDePasse);
    }

}
