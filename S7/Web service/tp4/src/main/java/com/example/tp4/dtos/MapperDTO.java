package com.example.tp4.dtos;


import com.example.tp4.modele.Question;
import org.springframework.stereotype.Component;

@Component
public class MapperDTO {

    public QuestionDTO convertirEnQuestionDTO(Question question) {
        return new QuestionDTO(question.getIdUtilisateur(),
                question.getLibelleQuestion(), question.getReponse(), question.getIdQuestion());
    }


    public Question convertirEnQuestion(QuestionDTO questionDTO) {
        return new Question(questionDTO.getIdUtilisateur(), questionDTO.getLibelleQuestion(), questionDTO.getReponse(), questionDTO.getIdQuestion());
    }


}
