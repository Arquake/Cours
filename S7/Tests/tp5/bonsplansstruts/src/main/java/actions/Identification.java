package actions;

import modele.exceptions.IdentifiantsIncorrectsException;

public class Identification extends Environnement {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String execute() throws Exception  {
        String token = null;
        try {
            token = this.getFacadeBonsPlans().identification(this.email, this.password);
            this.getSession().put("token", token);
            return SUCCESS;
        } catch (IdentifiantsIncorrectsException e) {
            addActionError("Identifiants incorrects");
            return INPUT;
        }

    }
}
