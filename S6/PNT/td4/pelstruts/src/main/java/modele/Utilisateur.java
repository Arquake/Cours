package modele;


public class Utilisateur {
    private final String login;
    private String password;
    public boolean isAdmin;

    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;
        this.isAdmin = false;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    private void setAdmin() {
        this.isAdmin = true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    // template methods
    public static Utilisateur create(String login, String password) {
        return new Utilisateur(login,password);
    }
    public static Utilisateur createAdmin(String login, String password) {
        Utilisateur u = new Utilisateur(login,password);
        u.setAdmin();
        return u;
    }
}
