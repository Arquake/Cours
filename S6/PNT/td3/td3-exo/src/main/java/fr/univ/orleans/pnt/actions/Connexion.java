package fr.univ.orleans.pnt.actions;


import org.apache.struts2.ActionSupport;

public class Connexion extends ActionSupport {


    public String showForm() throws Exception {
        return "connexionForm";
    }

    @Override
    public String execute() throws Exception {

        return "seConnecter";
    }
}
