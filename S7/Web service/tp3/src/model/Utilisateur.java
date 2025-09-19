public class Utilisateur {

    private int idUtilisateur;
    private static int IDS = 0;

    private String login;
    private String motDePasse;

    public Utilisateur(String login, String motDePasse) {
        this.idUtilisateur = IDS++;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public boolean verifierMotDePasse(String motDePasse){
        return this.motDePasse.equals(motDePasse);
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }
}
