package fr.univ.orleans.info.m1.ws.tp4.modele;

import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.AccessIllegalAUneQuestionException;
import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.QuestionInexistanteException;
import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.UtilisateurInexistantException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class FacadeApplication {

    /**
     * Les utilisateurs ne sont pas stockés ici, on n'utilise que leur identifiant integer.
     * On stocke ici toutes les questions posées par chaque utilisateur
     */
    private final Map<Integer, Collection<Question>> questionsParUtilisateur;

    private final Map<String, Question> questionsPosees;

    public FacadeApplication() {
        questionsParUtilisateur = new HashMap<>();
        questionsPosees = new HashMap<>();
    }

    /**
     * Permet à un utilisateur de poser une question.
     */
    public Question ajouterUneQuestion(int idUtilisateur, String libelleQuestion) {
        Question question = new Question(idUtilisateur, libelleQuestion);
        questionsPosees.put(question.getIdQuestion(), question);
        if (questionsParUtilisateur.containsKey(idUtilisateur)) {
            this.questionsParUtilisateur.get(idUtilisateur).add(question);
        } else {
            Collection<Question> questions = new ArrayList<>();
            questions.add(question);
            this.questionsParUtilisateur.put(idUtilisateur, questions);
        }
        return question;
    }

    /**
     * Ajoute/remplace une réponse à une question.
     */
    public void repondreAUneQuestion(String idQuestion, String reponse) throws QuestionInexistanteException {
        if (this.questionsPosees.containsKey(idQuestion)) {
            this.questionsPosees.get(idQuestion).setReponse(reponse);
        } else {
            throw new QuestionInexistanteException();
        }
    }

    /**
     * Retourne toutes les questions en attente de réponse.
     */
    public Collection<Question> getQuestionsSansReponses() {
        return this.questionsPosees.values().stream().filter(q -> Objects.isNull(
                q.getReponse()) || q.getReponse().isBlank()).collect(Collectors.<Question>toList());
    }

    /**
     * Retourne toutes les questions posées par un utilisateur et pour lesquelles quelqu'un a répondu.
     */
    public Collection<Question> getQuestionsAvecReponsesByUser(int idUtilisateur) throws UtilisateurInexistantException {
        if (this.questionsParUtilisateur.containsKey(idUtilisateur)) {
            return this.questionsParUtilisateur.get(idUtilisateur)
                    .stream().filter(q -> Objects.nonNull(q.getReponse())
                            && (!q.getReponse().isBlank())).collect(Collectors.toList());
        } else {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Retourne toutes les questions posées par un utilisateur et pour lesquelles personne n'a répondu.
     */
    public Collection<Question> getQuestionsSansReponsesByUser(int idUtilisateur) throws UtilisateurInexistantException {
        if (this.questionsParUtilisateur.containsKey(idUtilisateur)) {
            return this.questionsParUtilisateur.get(idUtilisateur)
                    .stream()
                    .filter(q -> Objects.isNull(q.getReponse()) || q.getReponse().isBlank()).collect(Collectors.toList());
        } else {
            throw new UtilisateurInexistantException();

        }
    }

    /**
     * Retourne toutes les questions posées par un utilisateur.
     */
    public Collection<Question> getToutesLesQuestionsByUser(int idUtilisateur) throws UtilisateurInexistantException {
        if (this.questionsParUtilisateur.containsKey(idUtilisateur)) {
            return this.questionsParUtilisateur.get(idUtilisateur);
        } else {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Retourne l'ensemble des questions posées.
     */
    public Collection<Question> getToutesLesQuestions() {
        return questionsPosees.values();
    }

    /**
     * Retourne une question d'un utilisateur, si cette question a bien été posée par cet utilisateur.
     */
    public Question getQuestionByIdPourUnUtilisateur(int idUtilisateur, String idQuestion) throws QuestionInexistanteException, AccessIllegalAUneQuestionException, UtilisateurInexistantException {
        Question question = questionsPosees.get(idQuestion);
        if (Objects.isNull(question))
            throw new QuestionInexistanteException();
        Collection<Question> questionsIdUtilisateur = this.questionsParUtilisateur.get(idUtilisateur);

        if (Objects.isNull(questionsIdUtilisateur)) {
            throw new UtilisateurInexistantException();
        }

        if (questionsIdUtilisateur.contains(question)) {
            return question;
        } else {
            throw new AccessIllegalAUneQuestionException();
        }
    }
}
