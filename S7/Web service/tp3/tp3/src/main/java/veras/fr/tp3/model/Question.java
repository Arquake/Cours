package veras.fr.tp3.model;

public class Question {

    private String idQuestion;
    private int idUtilisateur;
    private String libelleQuestion;
    private String reponse;

    public Question(int idUtilisateur, String question) {
        this.idUtilisateur = idUtilisateur;
        this.libelleQuestion = question;
        this.idQuestion = UUID.randomUUID().toString();
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLibelleQuestion() {
        return libelleQuestion;
    }

    public void setLibelleQuestion(String libelleQuestion) {
        this.libelleQuestion = libelleQuestion;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
