package veras.fr.tp3.factory;

import veras.fr.tp3.Dtos.QuestionDTOOut;
import veras.fr.tp3.model.Question;

import java.util.Collection;

public class QuestionFactory {

    public static QuestionDTOOut questionToQuestionOutDTO(Question question) {
        return new QuestionDTOOut(question.getLibelleQuestion(), question.getIdQuestion());
    }

    public static Collection<QuestionDTOOut> questionDTOOutCollection(Collection<Question> questions) {
        return questions.stream().map(QuestionFactory::questionToQuestionOutDTO).toList();
    }
}
