package paj.tp3.server;

import paj.tp3.server.Exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Server {
    private final HashMap<String, User> users = new HashMap<>();

    private final HashMap<String, User> usersByMail = new HashMap<>();
    private final HashMap<String, Sondage> sondages = new HashMap<>();
    private final HashMap<String, List<Sondage>> collaborations = new HashMap<>();

    private static Server instance;

    Server() {}

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    /**
     * Créé un utilisateur avec les données fournit
     * @param password l'utilisateur à créer
     * @param email l'email de l'utilisateur
     * @param nom le nom de l'utilisateur
     * @param prenom le prénom de l'uitlisateur
     * @throws EmailAlreadyInUseException L'email est déjà utilisé
     */
    public String createUser(String nom, String prenom, String email, String password) throws EmailAlreadyInUseException {
        User userToCreate = new User(nom, prenom, email, password, String.valueOf(UUID.randomUUID()));
        if ( usersByMail.get(userToCreate.getEmail()) != null) {
            throw new EmailAlreadyInUseException();
        }
        while (true) {
            if( users.get(userToCreate.getUuid()) == null){
                users.put(userToCreate.getUuid(), userToCreate);
                usersByMail.put(userToCreate.getEmail(), userToCreate);
                break;
            }
            userToCreate = new User(nom, prenom, email, password, String.valueOf(UUID.randomUUID()));
        }
        return userToCreate.getUuid();
    }

    /**
     * enregistre un sondage avec les paramètres fournit
     * @param libelle le libelle du sondage à créer
     * @param reponses les reponses possible du sondage
     * @throws ClientIsNotConnectedException Si le client n'est pas connecté
     * @throws UserDontExistException Si l'utilisateur n'existe pas
     */
    public void createPoll(AbstractUser user, String libelle, List<String> reponses) throws UserDontExistException, ClientIsNotConnectedException {
        User currentUser;
        Sondage sondage = new Sondage(libelle, reponses);

        try {
            currentUser = (User) user;
        } catch (Exception e) {
            throw new ClientIsNotConnectedException();
        }

        if (users.get(currentUser.getUuid()) == null) {
            throw new UserDontExistException();
        }

        while(true) {
            if( sondages.get(sondage.getUuid()) == null){
                sondages.put(sondage.getUuid(), sondage);
                break;
            }
            else {
                sondage = new Sondage(sondage.getLibelle(), reponses);
            }
        }
    }

    /**
     * Convertit les sondages en String
     * @return String de sondage
     */
    public HashMap<String,Sondage> listPolls() {
        StringBuilder pollList = new StringBuilder();
        return this.sondages;
    }

    /**
     * prend l'uuid d'un sondage et en retourne son string
     *
     * @param uuid l'uuid du sondage
     * @return le String du sondage sélectionné
     * @throws PollDontExistException Si le sondage n'existe pas
     */
    public Sondage getPoll(String uuid) throws PollDontExistException {
        if( sondages.get(uuid) == null ) {
            throw new PollDontExistException();
        }
        return sondages.get(uuid);
    }

    /**
     * récupère les sondages dans lesquelles l'utilisateur a participé
     * @param uuid l'uuid de l'utilisateur
     * @return la list de sondages auquel l'utilisateur a participé
     * @throws UserDontExistException si l'utilisateur n'existe pas
     */
    public List<Sondage> getPollCollaboratedIn(String uuid) throws UserDontExistException {
        if( collaborations.get(uuid) == null ) {
            throw new UserDontExistException();
        }

        return collaborations.get(uuid);
    }

    public void answerPoll(String userUuid, String pollUuid, int choice) throws AlreadyContributedException, UserDontExistException, ChoiceDontExistException, PollDontExistException {
        Sondage poll = sondages.get(pollUuid);
        List<Sondage> collabs = collaborations.get(userUuid);

        if (poll == null) {
            throw new PollDontExistException();
        }
        if( collabs == null ) {
            throw new UserDontExistException();
        }

        if ( collabs.contains(poll) ) {
            throw new AlreadyContributedException();
        }
        poll.addOneTo(choice);
        collabs.add(poll);
    }

    public User getProfileInfo(String uuid) throws UserDontExistException {
        if(users.get(uuid) == null) {
            throw new UserDontExistException();
        }
        return users.get(uuid);
    }

    public Sondage getInfoSondage(String uuid) throws PollDontExistException {
        if ( sondages.get(uuid) == null ) {
            throw new PollDontExistException();
        }
        return sondages.get(uuid);
    }

    /**
     * Supprime l'utilisateur de la base de donnée
     * @param uuid l'uuid du user
     */
    public void unsubscribe(String uuid) {
        usersByMail.remove(users.get(uuid).getEmail());
        users.remove(uuid);
        collaborations.remove(uuid);
    }
}
