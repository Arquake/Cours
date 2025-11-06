package veras.fr.tp3.factory;

import veras.fr.tp3.Dtos.QuestionDTOOut;
import veras.fr.tp3.model.Question;

public class QuestionFactory {

    public static QuestionDTOOut questionToQuestionOutDTO(Question question) {
        return new QuestionDTOOut(question.getLibelleQuestion(), question.getIdQuestion());
    }
}
