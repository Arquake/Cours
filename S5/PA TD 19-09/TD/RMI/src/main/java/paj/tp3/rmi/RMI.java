package paj.tp3.rmi;

import paj.tp3.abstracts.AbstractUser;
import paj.tp3.abstracts.Sondage;
import paj.tp3.abstracts.User;
import paj.tp3.rmi.exceptions.Exceptions.*;
import paj.tp3.server.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMI implements Remote {

    private static RMI instance;

    static Server server;

    RMI() {
        try {
            Registry server = LocateRegistry.getRegistry(null, 8097);
            this.server = (Server) server.lookup("Server");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static RMI getInstance() {
        if (instance == null) {
            instance = new RMI();
        }
        return instance;
    }

    /**
     * crée un utilisateur en utilisant les données fournit
     * @param nom le nom de l'utilisateur
     * @param prenom le prenom de l'utilisateur
     * @param email l'email de l'utilisateur
     * @param password le mot de pass de l'utilisateur
     * @throws InvalidInputException si une information n'est pas valide
     */
    public static AbstractUser createUser(String nom, String prenom, String email, String password) throws InvalidInputException, RemoteException {
        try {
            String uuid = server.createUser(nom, prenom, email, password);
            return new User(nom, prenom, email, password, uuid);
        } catch (EmailAlreadyInUseException e) {
            throw new InvalidInputException();
        }
    }

    public static void createPoll(AbstractUser user, String libelle, List<String> reponses) throws ClientIsNotConnectedException, UserDontExistException, RemoteException {
        try {
            server.createPoll(user, libelle, reponses);
        } catch (ClientIsNotConnectedException e) {
            throw new ClientIsNotConnectedException();
        } catch (UserDontExistException e) {
            throw new UserDontExistException();
        }
    }

    public static String listPolls() throws RemoteException {
        HashMap<String, Sondage> sondageList = server.listPolls();
        StringBuilder pollList = new StringBuilder();
        for(Map.Entry<String, Sondage> item : sondageList.entrySet()) {
            pollList.append(STR."\{item.getValue()}\n");
        }
        return pollList.isEmpty()? "pas de sondages !" : pollList.toString();
    }

    public static String getPoll(String uuid) throws PollDontExistException, RemoteException {
        try {
            return server.getPoll(uuid).toString();
        } catch (PollDontExistException e) {
            throw new PollDontExistException();
        }
    }

    public static String getPollCollaboratedIn(String uuid) throws UserDontExistException, RemoteException {
        List<Sondage> collabs = null;
        try {
            collabs = server.getPollCollaboratedIn(uuid);
        } catch (UserDontExistException e) {
            throw new UserDontExistException();
        }

        StringBuilder itemList = new StringBuilder();
        for( Sondage item : collabs ) {
            itemList.append(STR."\{item}\n");
        }

        return itemList.toString();
    }

    public static void answerPoll(String userUuid, String pollUuid, int choice) throws PollDontExistException, AlreadyContributedException, UserDontExistException, RemoteException {
        try {
            server.answerPoll(userUuid, pollUuid, choice);
        } catch (PollDontExistException e) {
            throw new PollDontExistException();
        } catch (AlreadyContributedException e) {
            throw new AlreadyContributedException();
        } catch (UserDontExistException e) {
            throw new UserDontExistException();
        }
    }

    public static String getProfileInfo(String uuid) throws UserDontExistException, RemoteException {
        try {
            return server.getProfileInfo(uuid).toString();
        } catch (UserDontExistException e) {
            throw new UserDontExistException();
        }
    }

    public static String getInfoSondage(String uuid) throws PollDontExistException, RemoteException {
        try {
            return server.getInfoSondage(uuid).toString();
        }
        catch (PollDontExistException e) {
            throw new PollDontExistException();
        }
    }

    public static void unsubscribe(String uuid) throws RemoteException {
        server.unsubscribe(uuid);
    }
}
