package paj.tp3.server;

import Exceptions.*;
import RMI.API;
import RMI.Exceptions.ClientIsNotConnectedException;

import java.rmi.RemoteException;
import java.util.List;

public class User extends AbstractUser {
    private final String nom;
    private final String prenom;
    private final String email;
    private final String uuid;
    private final String password;

    public User(String nom, String prenom, String email, String password, String uuid) {
        super(true);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }



    public void creerUnSondage(String libelle, List<String> responses) {
        try {
            API.createPoll(this, libelle, responses);
        } catch (ClientIsNotConnectedException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } catch (UserDontExistException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getSondages(){
        try {
            System.out.println(API.listPolls());
        }
        catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getOneSondage(String uuid) {
        try {
            System.out.println(API.getPoll(uuid));
        } catch (PollDontExistException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getSondageParticipations() {
        try {
            System.out.println(API.getPollCollaboratedIn(this.uuid));
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } catch (UserDontExistException e) {
            System.err.println(e.getMessage());
        }

    }

    public void answerSondage() {

        String input = getUserInput("L'uuid du sondage : ");
        String choice = this.getUserInput("La réponse de votre choix : ");

        try {

            this.verifyValidityChoice(choice);

            API.answerPoll(this.uuid, input, Integer.parseInt(choice));

        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
        } catch (ChoiceDontExistException e) {
            System.err.println(e.getMessage());
        } catch (PollDontExistException e) {
            System.err.println(e.getMessage());
        } catch (AlreadyContributedException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } catch (UserDontExistException e) {
            System.err.println(e.getMessage());
        }

    }

    public void getUserInfo() {
        try {
            System.out.println(API.getProfileInfo(this.uuid));
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } catch (UserDontExistException e) {
            System.err.println(e.getMessage());
        }

    }

    public void unSubscribe() {
        try {
            API.unsubscribe(this.uuid);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}


