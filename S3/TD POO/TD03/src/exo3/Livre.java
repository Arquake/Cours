package exo3;

public class Livre extends Document{
    private String auteur;
    private int nbJoursAutorise = 21;

    public Livre(int numDoc, String titre, String auteur) {
        super(numDoc, titre);
        this.auteur = auteur;
    }

    @Override
    public String toString(){
        return "auteur : "+auteur+" "+super.toString();
    }

    public void setNbJoursAutorise(int nbJoursAutorise) {
        this.nbJoursAutorise = nbJoursAutorise;
    }

    public int getNbJoursAutorises() {
        return nbJoursAutorise;
    }
}
