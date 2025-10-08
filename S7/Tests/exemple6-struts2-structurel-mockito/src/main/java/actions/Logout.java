package actions;

public class Logout extends Environnement {
    @Override
    public String execute() throws Exception {
        getFacade().deconnexion(getUtilisateur().getLogin());
        unsetUtilisateur();
        return SUCCESS;
    }
}
