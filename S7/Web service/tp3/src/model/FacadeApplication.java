public class FacadeApplication {

    /**
     * Questions posées par chaque utilisateur.
     * Les utilisateurs ne sont pas stockés ici ; on n'utilise que leur identifiant.
     */
    private Map<Integer, Collection<Question>> utilisateursQuestionsMap;

    /** Toutes les questions posées, identifiées par leur identifiant. */
    private Map<String, Question> questionsMap;

    public FacadeApplication() {
        utilisateursQuestionsMap = new HashMap<>();
        questionsMap = new HashMap<>();
    }

    /**
     * Poser une nouvelle question.
     * @param idUtilisateur l'utilisateur posant la question
     * @param question      l'intitulé de la question
     * @return l'identifiant aléatoire de la question créée, au format String.
     */
    public String ajouterUneQuestion(int idUtilisateur, String question) {
        Question question1 = new Question(idUtilisateur, question);
        questionsMap.put(question1.getIdQuestion(), question1);

        if (utilisateursQuestionsMap.containsKey(idUtilisateur)) {
            this.utilisateursQuestionsMap.get(idUtilisateur).add(question1);
        } else {
            Collection<Question> questions = new ArrayList<>();
            questions.add(question1);
            this.utilisateursQuestionsMap.put(idUtilisateur, questions);
        }

        return question1.getIdQuestion();
    }

    /**
     * Répondre à une question.
     * @param idQuestion l'identifiant de la question à laquelle réponse
     * @param reponse    la réponse proposée
     * @throws QuestionInexistanteException s'il n'existe pas de question avec l'identifiant donné
     */
    public void repondreAUneQuestion(String idQuestion, String reponse)
            throws QuestionInexistanteException {
        if (this.questionsMap.containsKey(idQuestion)) {
            this.questionsMap.get(idQuestion).setReponse(reponse);
        } else {
            throw new QuestionInexistanteException();
        }
    }

    /**
     * Retourne l'ensemble des questions posées par tous les utilisateurs.
     * @return une collection de toutes les questions posées.
     */
    public Collection<Question> getQuestions() {
        return questionsMap.values();
    }

    /**
     * Retourne l'ensemble des questions en attente de réponse.
     * @return une collection des questions en attente de réponse.
     */
    public Collection<Question> getQuestionsSansReponses() {
        return this.questionsMap.values().stream()
            .filter(q -> Objects.isNull(q.getReponse()) || q.getReponse().isBlank())
            .collect(Collectors.toList());
    }

    /**
     * Récupère toutes les questions d'un utilisateur.
     * @param idUtilisateur l'identifiant de l'utilisateur dont on veut les questions
     * @return une collection des questions de cet utilisateur.
     * @throws UtilisateurInexistantException s'il n'existe pas d'utilisateur avec l'identifiant donné
     */
    public Collection<Question> getToutesLesQuestionsByUser(int idUtilisateur)
            throws UtilisateurInexistantException {
        if (this.utilisateursQuestionsMap.containsKey(idUtilisateur)) {
            return this.utilisateursQuestionsMap.get(idUtilisateur);
        } else {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Récupère toutes les questions posées par un utilisateur, et qui ont reçu une réponse.
     * @param idUtilisateur l'identifiant de l'utilisateur dont on veut les questions
     * @return une collection des questions de cet utilisateur ayant reçu une réponse.
     * @throws UtilisateurInexistantException s'il n'existe pas d'utilisateur avec l'identifiant donné
     */
    public Collection<Question> getQuestionsAvecReponsesByUser(int idUtilisateur)
            throws UtilisateurInexistantException {
        if (this.utilisateursQuestionsMap.containsKey(idUtilisateur)) {
            return this.utilisateursQuestionsMap.get(idUtilisateur).stream()
                .filter(q -> Objects.nonNull(q.getReponse()) && (!q.getReponse().isBlank()))
                .collect(Collectors.toList());
        } else {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Récupère toutes les questions posées par un utilisateur, et qui sont en attente de réponse.
     * personne n'a répondu
     * @param idUtilisateur l'identifiant de l'utilisateur dont on veut les questions
     * @return une collection des questions de cet utilisateur en attente de réponse.
     * @throws UtilisateurInexistantException s'il n'existe pas d'utilisateur avec l'identifiant donné
     */
    public Collection<Question> getQuestionsSansReponsesByUser(int idUtilisateur)
            throws UtilisateurInexistantException {
        if (this.utilisateursQuestionsMap.containsKey(idUtilisateur)) {
            return this.utilisateursQuestionsMap.get(idUtilisateur).stream()
                    .filter(q -> Objects.isNull(q.getReponse()) || q.getReponse().isBlank())
                    .collect(Collectors.toList());
        } else {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Retourne une question posée par un utilisateur.
     * @param idUtilisateur l'identifiant de l'utilisateur dont on veut récupérer la question
     * @param idQuestion    l'identifiant de la question
     * @return la question posée.
     * @throws UtilisateurInexistantException     s'il n'existe pas d'utilisateur avec cet identifiant
     * @throws QuestionInexistanteException       s'il n'existe pas de question avec cet identifiant
     * @throws AccesIllegalAUneQuestionException si la question n'a pas été posée par cet utilisateur
     */

    public Question getQuestionByIdPourUnUtilisateur(int idUtilisateur,String idQuestion)
            throws QuestionInexistanteException, AccesIllegalAUneQuestionException,
                UtilisateurInexistantException {
        Question question = questionsMap.get(idQuestion);
        if (Objects.isNull(question)) {
            throw new QuestionInexistanteException();
        }

        Collection<Question> questionsIdUtilisateur = this.utilisateursQuestionsMap.get(idUtilisateur);
        if (Objects.isNull(questionsIdUtilisateur)) {
            throw new UtilisateurInexistantException();
        }

        if (questionsIdUtilisateur.contains(question)) {
            return question;
        } else {
            throw new AccesIllegalAUneQuestionException();
        }
    }
}