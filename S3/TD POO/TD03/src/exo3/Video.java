package exo3;

public class Video extends Document{
    private String realisateur;
    private int nbJoursAutorise = 14;

    public Video(int numDoc, String titre, String realisateur) {
        super(numDoc, titre);
        this.realisateur = realisateur;
    }

    @Override
    public String toString() {
        return "Video{" +
                "realisateur='" + realisateur + '\'' +
                ", numDoc=" + numDoc +
                ", titre='" + titre + '\'' +
                '}';
    }

    public void setNbJoursAutorise(int nbJoursAutorise) {
        this.nbJoursAutorise = nbJoursAutorise;
    }

    public int getNbJoursAutorises() {
        return nbJoursAutorise;
    }
}
