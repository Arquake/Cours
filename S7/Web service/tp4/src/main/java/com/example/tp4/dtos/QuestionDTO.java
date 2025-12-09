package com.example.tp4.dtos;

import java.util.UUID;

public class QuestionDTO {
    private String idUtilisateur;
    private String libelleQuestion;
    private String reponse;
    private String idQuestion;

    public QuestionDTO() {
    }

    public QuestionDTO(String idUtilisateur, String libelleQuestion, String reponse, String idQuestion) {
        this.idUtilisateur = idUtilisateur;
        this.libelleQuestion = libelleQuestion;
        this.reponse = reponse;
        this.idQuestion = idQuestion;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setLibelleQuestion(String libelleQuestion) {
        this.libelleQuestion = libelleQuestion;
    }


    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getLibelleQuestion() {
        return libelleQuestion;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
