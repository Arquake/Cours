package fr.univ.orleans.pnt.actions;


import org.apache.struts2.ActionSupport;

public class Saisie extends ActionSupport {

    private String pseudo;
    private String password;

    @Override
    public String execute() throws Exception {
        if (pseudo.length() < 3 && password.length() < 3) {
            addActionError("Le login ou mot de passe est trop court");
            return "errorSaisie";
        }
        return "showSaisie";
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
